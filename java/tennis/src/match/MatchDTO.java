package match;

import constants.Constants;
import util.Util;

public class MatchDTO {
    private int idCounter = Integer.parseInt(Util.getCurrentId(Constants.MATCH_FILE_PATH));
    private int matchId = idCounter;
    private int player1Id;
    private int player2Id;
    private int winnerId;
    private int currentSet;
    private int numSets;
    private String setScores;
    private String pointScore;
    private String totalSetScore;

    private String player1;    // Match.csv 파일 첫번째 선수 이름
    private String player2; // Match.csv 파일 두번째 선수 이름
    private String winner;  // Match.csv 파일 게임 승자
    private String setOfgame;    // Match.csv 파일 세트수
    private String gameScore;  // Match.csv 파일 게임 스코어

    public MatchDTO(int player1Id, int player2Id, int winnerId, int currentSet, int numSets, String setScores,
                    String pointScore, String totalSetScore) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
        this.currentSet = currentSet;
        this.numSets = numSets;
        this.setScores = setScores;
        this.pointScore = pointScore;
        this.totalSetScore = totalSetScore;
    }

    public MatchDTO(int matchId, int player1Id, int player2Id, int winnerId, int currentSet, int numSets,
                    String setScores, String pointScore, String totalSetScore) {
        this.matchId = matchId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
        this.currentSet = currentSet;
        this.numSets = numSets;
        this.setScores = setScores;
        this.pointScore = pointScore;
        this.totalSetScore = totalSetScore;
    }

    public MatchDTO() {
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int getNumSets() {
        return numSets;
    }

    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }

    public String getSetScores() {
        return setScores;
    }

    public void setSetScores(String setScores) {
        this.setScores = setScores;
    }

    public String getPointScore() {
        return pointScore;
    }

    public void setPointScore(String pointScore) {
        this.pointScore = pointScore;
    }

    public String getTotalSetScore() {
        return totalSetScore;
    }

    public void setTotalSetScore(String totalSetScore) {
        this.totalSetScore = totalSetScore;
    }

    // ==================================================================

    public String getSetOfgame() {
        return setOfgame;
    }

    public void setSetOfgame(String setOfgame) {
        this.setOfgame = setOfgame;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getGameScore() {
        return gameScore;
    }

    public void setGameScore(String gameScore) {
        this.gameScore = gameScore;
    }

    @Override
    public String toString() {
        return "MatchDTO [idCounter=" + idCounter + ", matchId=" + matchId + ", player1Id=" + player1Id + ", player2Id="
                + player2Id + ", winnerId=" + winnerId + ", currentSet=" + currentSet + ", numSets=" + numSets
                + ", setScore=" + setScores + ", pointScore=" + pointScore + ", totalSetScore=" + totalSetScore + "]";
    }

}