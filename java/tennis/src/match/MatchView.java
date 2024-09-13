package match;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.lalyos.jfiglet.FigletFont;
import player.PlayerDAO;
import player.PlayerDTO;
import player.PlayerService;
import util.AnimationView;
import util.Util;

public class MatchView {
    // 게임 시작 메뉴
    public static void matchMenu(Scanner scanner) {
        while (true) {
            int playerCount = PlayerDAO.getTotalPlayerNumber();
            if (playerCount == 0) {
                System.out.println("최소 2명 이상의 플레이어가 있어야 합니다.");
                return;
            }

            Util.printTitle("게임 시작");
            System.out.println("1. 새 게임 🏸");
            System.out.println("2. 저장된 경기 💾");
            System.out.println("3. 뒤로가기 🚪");
            System.out.println();
            System.out.print("➡ 옵션을 선택하세요: ");

            String choice = "";

            while (choice.isEmpty()) {
                choice = scanner.nextLine();
                if (choice.equals("1")) {
                    startNewGame(scanner);
                } else if (choice.equals("2")) {
                    MatchService.loadSavedMatch(scanner);
                } else if (choice.equals("3")) {
                    return;
                } else {
                    System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
                }
            }
        }
    }

    // 새 게임 메뉴
    private static void startNewGame(Scanner scanner) {
        PlayerDTO player1 = null;
        PlayerDTO player2 = null;

        while (player1 == null) {
            System.out.println();
            System.out.print("선수 1 이름: ");
            String player1Name = scanner.nextLine();
            player1 = PlayerService.getByName(player1Name);

            if (player1 == null) {
                System.out.println();
                System.out.println(player1Name + "(을)를 찾을 수 없습니다.");

                System.out.println("1. 다시 찾기 ");
                System.out.println("2. 뒤로 가기");
                System.out.println();
                System.out.print("➡ 옵션을 선택하세요: ");

                String choice = scanner.nextLine();

                if (choice.equals("2")) {
                    return;
                }
            }
        }

        while (player2 == null) {
            System.out.print("선수 2 이름: ");
            String player2Name = scanner.nextLine();
            player2 = PlayerService.getByName(player2Name);

            if (player2 == null) {
                System.out.println();
                System.out.println(player2Name + "(을)를 찾을 수 없습니다.");

                System.out.println("1. 다시 찾기");
                System.out.println("2. 뒤로 가기");
                System.out.println();
                System.out.print("➡ 옵션을 선택하세요: ");

                String choice = scanner.nextLine();

                if (choice.equals("2")) {
                    return;
                }
            }
        }

        String choice = "";
        int numSets = 0;

        while (choice.isEmpty()) {
            System.out.println();
            System.out.println("세트 수 선택: ");
            System.out.println("1. 3세트 경기");
            System.out.println("2. 5세트 경기");
            System.out.println();
            System.out.print("➡ 옵션을 선택하세요: ");

            choice = scanner.nextLine();

            if (choice.equals("1")) {
                numSets = 3;
            } else if (choice.equals("2")) {
                numSets = 5;
            } else {
                System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
                System.out.println();
                choice = "";
            }
        }

        System.out.println("새 게임을 시작합니다.");
        System.out.println();

        MatchService.startNewMatch(scanner, player1.getPlayerId(), player2.getPlayerId(), numSets);
    }

    // 저장된 게임 목록
    public static MatchDTO displaySavedMatches(Scanner scanner, List<MatchDTO> matchList) {
        int size = matchList.size();
        if(size == 0) {
            System.out.println();
            System.out.println("저장된 경기가 없습니다.");
            return null;
        }

        String choice;
        int intChoice = -1;

        while (intChoice == -1) {
            Util.printTitle("저장된 경기 목록");
            System.out.println("0. 뒤로가기");
            System.out.println();

            for (int i = 0; i < size; i++) {
                MatchDTO matchDTO = matchList.get(i);
                String player1Name = PlayerDAO.getById(matchDTO.getPlayer1Id()).getName();
                String player2Name = PlayerDAO.getById(matchDTO.getPlayer2Id()).getName();
                String totalSetScore = matchDTO.getTotalSetScore();
                int numSets = matchDTO.getNumSets();
                int currentSet = matchDTO.getCurrentSet();

                System.out.printf("%d. %s vs %s\n", i + 1, player1Name, player2Name);
                System.out.printf("\t- 총 스코어 %s, 전체 %d세트 중 %d세트 진행중\n", totalSetScore, numSets, currentSet);
                if(i != size - 1) {
                    System.out.println();
                }
            }

            System.out.println();
            System.out.print("➡ 옵션을 선택하세요: ");
            choice = scanner.nextLine();

            try {
                intChoice = Integer.parseInt(choice);

                if (intChoice < 0 || intChoice > size) {
                    System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
                    intChoice = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
                intChoice = -1;
            }
        }

        if (intChoice == 0) { // 뒤로가기
            return null;
        }

        return matchList.get(intChoice - 1);
    }

    // 인게임 프롬프트
    public static boolean displayScoreAndPromptContinue(Scanner scanner, int[][] setScores, int[] pointScore, PlayerDTO player1DTO, PlayerDTO player2DTO, AtomicBoolean isTieBreak, int setIndex, int matchWinnerIdx) {
        boolean isAutoPlay = false; // 게임 자동 실행

        if (matchWinnerIdx == -1) {
            scoreBoard(
                    setScores,
                    pointScore,
                    player1DTO,
                    player2DTO,
                    matchWinnerIdx,
                    isTieBreak,
                    setIndex);

            if(!isAutoPlay) {
                System.out.println();
                System.out.println("계속 진행하시려면 Enter");
                System.out.println("중단 하시려면 아무 키나 입력해주세요.");
                String input = scanner.nextLine();
                return !input.isEmpty();
            }
            return false;
        } else {
            AnimationView.replayTitle(800, 10, 3);
            AnimationView.replayMovie(150, 10);
            AnimationView.winner(800);

            String winnerName = "";

            if (matchWinnerIdx == 0) {
                winnerName = player1DTO.getName();
            }

            if (matchWinnerIdx == 1) {
                winnerName = player2DTO.getName();
            }

            scoreBoard(
                    setScores,
                    pointScore,
                    player1DTO,
                    player2DTO,
                    matchWinnerIdx,
                    isTieBreak,
                    setIndex);

            System.out.printf("🎉 %s님의 승리로 게임이 끝났습니다.\n", winnerName);
            System.out.println();

            String input = "";
            System.out.print("아무 키나 입력하여 되돌아가기: ");

            while (input.isEmpty()) {
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.print("\r아무 키나 입력하여 되돌아가기: ");
                }
            }

            return true;
        }
    }

    public static void scoreBoard(int[][] setScores, int[] intPointScore, PlayerDTO player1, PlayerDTO player2, int winnerIndex, AtomicBoolean isTieBreak, int setIndex) {
        String player1Name = player1.getName();
        String player2Name = player2.getName();
        String[] pointScores = {convertScore(intPointScore, 0, isTieBreak), convertScore(intPointScore, 1, isTieBreak)};
        String relativeFilePath = "flf/5lineoblique.flf";


        int nameLength = 12;
        int maxNameLength = Math.max(player1Name.length(), player2Name.length());

        if (nameLength < maxNameLength) {
            nameLength = maxNameLength;
        }

        System.out.println();
        System.out.println();

        if (isTieBreak.get()) {
            System.out.printf("[%d세트 TIEBREAK 경기]\n", setIndex + 1);
        } else if (winnerIndex != -1) {
            try {
                File file = new File(relativeFilePath);
                if(file.exists()){
                    String asciiArt3 = FigletFont.convertOneLine(file, "  SCORE");
                    System.out.println(asciiArt3);
                } else {
                    System.out.println("[최종 스코어]");
                }

            } catch (Exception e) {
                System.out.println("MatchView.scoreBoard.asciiArt");
                e.printStackTrace();
            }
            // System.out.println("[최종 스코어]");
        } else {
            System.out.printf("[%d세트 진행중]\n", setIndex + 1);
        }

        splitLine(setScores, nameLength, winnerIndex);
        System.out.println();

        // 세트 배열
        for (int i = 0; i < setScores.length; i++) {
            System.out.printf("%10s", centerString((i + 1) + "SET", 10));
        }

        if (winnerIndex == -1) {
            System.out.printf("%10s", centerString("POINT", 10));
        }


        if (winnerIndex != -1) {
            System.out.printf("%10s", centerString("WINNER", 10));
        }

        System.out.print(centerString("선수", nameLength));

        System.out.println();
        splitLine(setScores, nameLength, winnerIndex);
        System.out.println();

        // 선수1 -----------------------------------------------------
        // 선수1 게임 스코어
        for (int i = 0; i < setScores.length; i++) {
            int score = setScores[i][0];
            System.out.printf("%10s", centerString(String.valueOf(winnerIndex != -1 && i >= setIndex && score == 0 ? "-" : score), 10));
        }

        // 선수1 스코어 + 승/패 구현
        if (winnerIndex == -1) {
            System.out.printf("%10s", centerString(pointScores[0], 10));
        }

        if (winnerIndex == 0) {
            System.out.printf("%10s", centerString("🥇", 10));
        }

        if (winnerIndex == 1) {
            System.out.printf("%10s", centerString("☠️", 10));
        }

        System.out.print(centerString(player1Name, nameLength));

        System.out.println();
        splitLine(setScores, nameLength, winnerIndex);
        System.out.println();

        // 선수2 -----------------------------------------------------
        // 선수2 게임 스코어
        for (int i = 0; i < setScores.length; i++) {
            int score = setScores[i][1];
            System.out.printf("%10s", centerString(String.valueOf(winnerIndex != -1 && i >= setIndex && score == 0 ? "-" : score), 10));
        }

        // 선수2 스코어 + 승/패 구현
        if (winnerIndex == -1) {
            System.out.printf("%10s", centerString(pointScores[1], 10));
        }

        if (winnerIndex == 1) {
            System.out.printf("%10s", centerString("🥇", 10));
        }

        if (winnerIndex == 0) {
            System.out.printf("%10s", centerString("☠️", 10));
        }

        System.out.print(centerString(player2Name, nameLength));

        System.out.println();
        splitLine(setScores, nameLength, winnerIndex);
        System.out.println();
    }

    private static void splitLine(int[][] setScores, int nameLength, int winnerIndex) {
        drawLine(nameLength);
        drawLine(setScores.length * 10); // 세트 점수 칸 수
        drawLine(10); // POINT or WINNER 칸
    }

    private static void drawLine(int length) {
        String splitter = "-";
        for (int i = 0; i < length; i++) {
            System.out.print(splitter);
        }
    }

    private static String centerString(String text, int width) {
        int textWidth = getStringWidth(text);
        if (textWidth >= width) {
            return text;
        }

        int padding = (width - textWidth) / 2;
        String format = "%" + padding + "s%s%" + padding + "s";

        if ((width - textWidth) % 2 != 0) {
            return String.format(format, "", text, " ");
        } else {
            return String.format(format, "", text, "");
        }
    }

    private static int getStringWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            if (c >= '\uAC00' && c <= '\uD7A3') {
                // 한글
                width += 2;
            } else {
                // 기타 문자 (영어, 숫자 등)
                width += 1;
            }
        }
        return width;
    }

    private static String convertScore(int[] pointScores, int index, AtomicBoolean isTieBreak) {
        final String ADVANTAGE_MARK = "AD";
        int deuceScore = isTieBreak.get() ? 7 : 3;
        boolean isDeuce = pointScores[0] >= deuceScore && pointScores[1] >= deuceScore;
        String result;

        if (isDeuce) {

            result = getRegularScore(pointScores[index]);

            if (pointScores[0] - pointScores[1] == 1) {
                if (index == 0) {
                    result = ADVANTAGE_MARK;
                } else {
                    result = "";
                }

            } else if (pointScores[1] - pointScores[0] == 1) {
                if (index == 1) {
                    result = ADVANTAGE_MARK;
                } else {
                    result = "";
                }
            }
        } else {
            if (isTieBreak.get()) {
                result = String.valueOf(pointScores[index]);
            } else {
                result = getRegularScore(pointScores[index]);
            }
        }

        return result;
    }

    private static String getRegularScore(int score) {
        if (score == 0) {
            return "0";
        } else if (score == 1) {
            return "15";
        } else if (score == 2) {
            return "30";
        } else {
            return "40";
        }
    }
}

