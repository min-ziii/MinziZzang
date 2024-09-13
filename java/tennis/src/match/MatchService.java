package match;

import player.PlayerDTO;
import player.PlayerService;
import util.AnimationView;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class MatchService {
    private static final String SET_SEPARATOR = "/";
    private static final String SCORE_SEPARATOR = ":";

    // 새 게임
    public static void startNewMatch(Scanner scanner, int player1Id, int player2Id, int numSets) {
        MatchDTO match = MatchDAO.newMatch(player1Id, player2Id, numSets);
        AnimationView.matchStart(1000, 8, 3);
        playMatch(scanner, match);
    }

    // 저장된 게임
    public static void loadSavedMatch(Scanner scanner) {
        MatchDTO chosenMatchDTO = MatchView.displaySavedMatches(scanner, MatchDAO.getSavedMatches());

        if (chosenMatchDTO == null) {
            return;
        }

        playMatch(scanner, chosenMatchDTO);
    }

    // 인게임
    private static void playMatch(Scanner scanner, MatchDTO match) {
        int[] playerIds = {match.getPlayer1Id(), match.getPlayer2Id()};
        PlayerDTO player1DTO = PlayerService.getById(playerIds[0]);
        PlayerDTO player2DTO = PlayerService.getById(playerIds[1]);

        String setScoreStr = match.getSetScores();
        String pointScoreStr = match.getPointScore();
        String totalSetScoreStr = match.getTotalSetScore();
        int numSets = match.getNumSets();
        int setIndex = match.getCurrentSet() - 1;

        int[][] setScores = new int[numSets][2];
        int[] totalSetScore = new int[2];
        int[] gameScore = new int[2];
        int[] pointScore = new int[2];

        AtomicBoolean isTieBreak = new AtomicBoolean(false);

        // 점수 불러오기
        loadScores(setScoreStr, pointScoreStr, totalSetScoreStr, totalSetScore, setScores, gameScore, pointScore, setIndex);

        int matchWinnerIdx = matchWinner(totalSetScore, numSets);
        boolean matchNotWon = matchWinnerIdx == -1;
        while (matchNotWon) { // 전체 세트 중 과반 승리 => 게임 승

            int setWinnerIdx = setWinner(gameScore);
            boolean setNotWon = setWinnerIdx == -1;
            while (setNotWon) { // 6게임 먼저 이기거나, 타이브레이크(6:6) 후 7점 먼저 얻기 => 세트 승

                int gameWinnerIdx = gameWinner(pointScore, isTieBreak);
                boolean gameNotWon = gameWinnerIdx == -1;
                while (gameNotWon) { // 4점 먼저 얻거나, 듀스(3:3) 후 2점차 => 게임 승
                    if (MatchView.displayScoreAndPromptContinue(scanner, setScores, pointScore, player1DTO, player2DTO, isTieBreak, setIndex, matchWinnerIdx)) {
                        return;
                    }

                    playGame(pointScore);

                    match.setPointScore(stringifyScores(pointScore));
                    MatchDAO.editMatch(match);

                    gameWinnerIdx = gameWinner(pointScore, isTieBreak);
                    gameNotWon = gameWinnerIdx == -1;
                } // ---- 게임 종료 ----

                // 게임결과 세트 반영
                gameScore[gameWinnerIdx]++;
                setScores[setIndex] = new int[]{gameScore[0], gameScore[1]};

                // 게임 초기화
                resetScores(pointScore);
                match.setPointScore(stringifyScores(pointScore));

                match.setSetScores(stringifySetScores(setScores, setIndex));
                MatchDAO.editMatch(match);

                // 타이브레이크 체크
                checkTieBreak(gameScore, isTieBreak);

                setWinnerIdx = setWinner(gameScore);
                setNotWon = setWinnerIdx == -1;
            } // ---- 세트 종료 ----

            totalSetScore[setWinnerIdx]++;
            match.setTotalSetScore(stringifyScores(totalSetScore));
            setScores[setIndex] = new int[]{gameScore[0], gameScore[1]};
            match.setSetScores(stringifySetScores(setScores, setIndex));

            setIndex++; // 다음 세트
            isTieBreak.set(false); // 타이브레이크 풀기
            match.setCurrentSet(setIndex + 1);
            MatchDAO.editMatch(match);

            resetScores(gameScore); // 현재 세트점수 초기화

            matchWinnerIdx = matchWinner(totalSetScore, numSets);
            matchNotWon = matchWinnerIdx == -1;
        } // ---- 매치 종료 ----


        // 매치 쓰기
        match.setWinnerId(playerIds[matchWinnerIdx]);
        MatchDAO.editMatch(match);

        updateWinLose(playerIds[0], playerIds[1], matchWinnerIdx);
        MatchView.displayScoreAndPromptContinue(scanner, setScores, pointScore, player1DTO, player2DTO, isTieBreak, setIndex, matchWinnerIdx);
    }


    private static void loadScores(String setScoreStr, String pointScoreStr, String totalSetScoreStr, int[] totalSetScore, int[][] setScores,
                                   int[] gameScore, int[] pointScore, int currentSetIdx) {
        String[] setStrArr = setScoreStr.split(SET_SEPARATOR);
        String[] pointScoreStrArr = pointScoreStr.split(SCORE_SEPARATOR);
        String[] totalSetScoreArr = totalSetScoreStr.split(SCORE_SEPARATOR);

        // 현재 세트 점수 불러오기
        int[] currentSetScore = Arrays.stream((setStrArr.length <= currentSetIdx ? "0:0" : setStrArr[currentSetIdx]).split(SCORE_SEPARATOR)).mapToInt(Integer::parseInt).toArray();
        gameScore[0] = currentSetScore[0];
        gameScore[1] = currentSetScore[1];

        // 총합 세트 점수 불러오기
        int[] currentTotalSetScore = Arrays.stream(totalSetScoreArr).mapToInt(Integer::parseInt).toArray();
        totalSetScore[0] = currentTotalSetScore[0];
        totalSetScore[1] = currentTotalSetScore[1];

        // 전체 세트 점수 불러오기
        for (int i = 0; i < setStrArr.length; i++) {
            int[] currentSet = Arrays.stream(setStrArr[i].split(SCORE_SEPARATOR)).mapToInt(Integer::parseInt).toArray();
            setScores[i] = new int[]{currentSet[0], currentSet[1]};
        }

        // 포인트 점수 로드
        int[] pointScoreArr = Arrays.stream(pointScoreStrArr).mapToInt(Integer::parseInt).toArray();
        pointScore[0] = pointScoreArr[0];
        pointScore[1] = pointScoreArr[1];
    }

    private static void playGame(int[] pointScores) {
        if (isPlayer1Win()) {
            pointScores[0]++;
        } else {
            pointScores[1]++;
        }
    }

    private static int matchWinner(int[] setWinCount, int numSets) {
        final int SET_TO_WIN = (numSets / 2) + 1;
        int p1WinCount = setWinCount[0];
        int p2WinCount = setWinCount[1];

        if (p1WinCount == SET_TO_WIN) {
            return 0;
        }

        if (p2WinCount == SET_TO_WIN) {
            return 1;
        }

        return -1;
    }

    private static int setWinner(int[] gameScore) {
        int p1Score = gameScore[0];
        int p2Score = gameScore[1];

        if (p1Score >= 6 || p2Score >= 6) {
            if (p1Score - p2Score >= 2) {
                return 0;
            }

            if (p2Score - p1Score >= 2) {
                return 1;
            }

            // 타이 브레이크
            if (p1Score >= 6 && p2Score >= 6) {
                if (p1Score == 7) {
                    return 0;
                }

                if (p2Score == 7) {
                    return 1;
                }
            }
        }

        return -1;
    }

    private static int gameWinner(int[] pointScore, AtomicBoolean isTiebreak) {
        int p1Score = pointScore[0];
        int p2Score = pointScore[1];

        if (isTiebreak.get()) {
            if (p1Score >= 7 && p1Score - p2Score >= 2) {
                return 0;
            }
            if (p2Score >= 7 && p2Score - p1Score >= 2) {
                return 1;
            }
        } else {
            if (p1Score >= 4 && p1Score - p2Score >= 2) {
                return 0;
            }
            if (p2Score >= 4 && p2Score - p1Score >= 2) {
                return 1;
            }
        }

        return -1;
    }

    private static void resetScores(int[] scores) {
        scores[0] = 0;
        scores[1] = 0;
    }

    private static boolean isPlayer1Win() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static String stringifySetScores(int[][] setScores, int setIndex) {
        String result = "";

        for (int i = 0; i <= setIndex; i++) {
            result += stringifyScores(setScores[i]);

            if ((i == setIndex)) {
                int[] lastGameScore = setScores[i];
                if (setWinner(lastGameScore) == -1) {
                    result += "";
                } else {
                    result += "/";
                }
            } else {
                result += "/";
            }
        }

        return result;
    }

    private static String stringifyScores(int[] scores) {
        return scores[0] + SCORE_SEPARATOR + scores[1];
    }

    private static void checkTieBreak(int[] gameScore, AtomicBoolean isTieBreak) {
        int p1Score = gameScore[0];
        int p2Score = gameScore[1];

        if (p1Score >= 6 && p2Score >= 6) {
            if (!isTieBreak.get()) {
                isTieBreak.set(true);
            }
        } else {
            if (isTieBreak.get()) {
                isTieBreak.set(false);
            }
        }
    }

    private static void updateWinLose(int player1Id, int player2Id, int matchWinnerIdx) {
        if (matchWinnerIdx == -1) {
            return;
        }

        if(matchWinnerIdx == 0) {
            PlayerService.increaseWin(player1Id);
            PlayerService.increaseLose(player2Id);
        }

        if(matchWinnerIdx == 1){
            PlayerService.increaseWin(player2Id);
            PlayerService.increaseLose(player1Id);
        }
    }

}
