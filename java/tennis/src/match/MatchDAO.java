package match;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;

public class MatchDAO {
    private final static String PATH = Constants.MATCH_FILE_PATH;
    private static final int NO_WINNER_ID = -1;
    private static final String NEW_SCORE = "0:0";
    private static final String FIELD_SEPARATOR = ",";
    private static final int INIT_CURRENT_SET = 1;

    public static MatchDTO getMatch(int matchId) {
        MatchDTO match = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line;
            while ((line = reader.readLine()) != null) {


                if (line.startsWith(String.valueOf(matchId))) {
                	String[] temp = line.split(FIELD_SEPARATOR);

                    int player1Id = Integer.parseInt(temp[1]);
                    int player2Id = Integer.parseInt(temp[2]);
                    int winnerId = Integer.parseInt(temp[3]);
                    int currentSet = Integer.parseInt(temp[4]);
                    int numSets = Integer.parseInt(temp[5]);
                    String setScore = temp[6];
                    String pointScore = temp[7];
                    String totalSetScore = temp[8];

                    match = new MatchDTO(matchId, player1Id, player2Id, winnerId, currentSet, numSets, setScore, pointScore, totalSetScore);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("MatchDAO.getGame");
            e.printStackTrace();
        }

        return match;
    }

    public static List<MatchDTO> getSavedMatches() {
        List<MatchDTO> unFinishedGames = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(FIELD_SEPARATOR);
                int winnerId = Integer.parseInt(temp[3]);
                if (winnerId == NO_WINNER_ID) {
                    int matchId = Integer.parseInt(temp[0]);
                    int player1Id = Integer.parseInt(temp[1]);
                    int player2Id = Integer.parseInt(temp[2]);
                    int currentSet = Integer.parseInt(temp[4]);
                    int numSets = Integer.parseInt(temp[5]);
                    String setScore = temp[6];
                    String pointScore = temp[7];
                    String totalSetScore = temp[8];

                    unFinishedGames.add(new MatchDTO(matchId, player1Id, player2Id, NO_WINNER_ID, currentSet, numSets, setScore, pointScore, totalSetScore));
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("MatchDAO.getSavedGames");
            e.printStackTrace();
        }

        return unFinishedGames;
    }

    public static MatchDTO newMatch(int player1Id, int player2Id, int numSets) {
        MatchDTO newMatch = new MatchDTO(player1Id, player2Id, NO_WINNER_ID, 1, numSets, NEW_SCORE, NEW_SCORE, NEW_SCORE);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true));

            writer.write(newMatch.getMatchId() + FIELD_SEPARATOR + player1Id + FIELD_SEPARATOR + player2Id + FIELD_SEPARATOR + NO_WINNER_ID + FIELD_SEPARATOR + INIT_CURRENT_SET + FIELD_SEPARATOR + numSets +  FIELD_SEPARATOR + NEW_SCORE + FIELD_SEPARATOR + NEW_SCORE + FIELD_SEPARATOR + NEW_SCORE + "\r\n");

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newMatch;
    }

    public static void editMatch(MatchDTO match) {
		try {

			//기존 점수 > 수정
			BufferedReader reader = new BufferedReader(new FileReader(PATH));

			String line = null;
			String temp = "";
			int matchId = match.getMatchId();

			while ((line = reader.readLine()) != null) {
                if (line.startsWith(String.valueOf(matchId))) {
                	temp +=
                	String.format(
                			"%s,%s,%s,%s,%d,%d,%s,%s,%s\r\n",
                			matchId,
                			match.getPlayer1Id(),
                			match.getPlayer2Id(),
                			match.getWinnerId(),
                			match.getCurrentSet(),
                			match.getNumSets(),
                			match.getSetScores(),
                			match.getPointScore(),
                            match.getTotalSetScore()
                			);
                } else {
                	temp += line + "\r\n";
                }
			}

			reader.close();

			//수정된 내용 > 덮어쓰기
			BufferedWriter writer
			= new BufferedWriter(new FileWriter(PATH));

			writer.write(temp);

			writer.close();

		} catch (Exception e) {
            System.out.println("MatchDAO.editMatch");
			e.printStackTrace();
		}

    }

	public static List<MatchDTO> playerToMatch(String playerName) {
		 List<MatchDTO> matches = new ArrayList<>();
		 try {
			BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
			BufferedReader matchReader = new BufferedReader(new FileReader(Constants.MATCH_FILE_PATH));
			String playerLine;
			String matchLine;
			while((playerLine = playerReader.readLine()) != null) {
				String[] playertemp = playerLine.split(",");
				if(playertemp[1].equals(playerName)) {
					while ((matchLine = matchReader.readLine()) != null) {
						String[] matchtemp = matchLine.split(",");
                       if (matchtemp[1].equals(playertemp[0])||matchtemp[2].equals(playertemp[0])) {
                           MatchDTO matchdto = new MatchDTO();
                           matchdto.setPlayer1(NumToKor(matchtemp[1]));
                           matchdto.setPlayer2(NumToKor(matchtemp[2]));
                           int setOfgameInt = Integer.parseInt(matchtemp[4]) - 1;
                           matchdto.setSetOfgame(Integer.toString(setOfgameInt));
                           matchdto.setWinner(NumToKor(matchtemp[3]));
                           if (matchtemp[3].equals("-1")) {
                        	   break;
                           }
                           matchdto.setGameScore(matchtemp[6]);
                           matches.add(matchdto);
                       }
					}
				}
			}
			playerReader.close();
           matchReader.close();
		} catch (Exception e) {
			System.out.println("TennisDAO.m1");
			e.printStackTrace();
		}
		return matches;
	 }


	 private static String NumToKor(String playerId) {
	        try {
	            BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
	            String playerLine;
	            while ((playerLine = playerReader.readLine()) != null) {
	                String[] temp = playerLine.split(",");
	                if (temp[0].equals(playerId)) {
	                    playerReader.close();
	                    return temp[1];
	                }
	            }
	            playerReader.close();
	        } catch (Exception e) {
	            System.out.println("TennisDAO.getPlayerNameById");
	            e.printStackTrace();
	        }
	        return null;
	    }

}
