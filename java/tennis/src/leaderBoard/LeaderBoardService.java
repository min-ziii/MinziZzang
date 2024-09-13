package leaderBoard;

import java.util.List;

import match.MatchDAO;
import match.MatchDTO;
import player.PlayerDAO;
import player.PlayerDTO;

public class LeaderBoardService {

	// 전적검색==============================================
	public static void relativeBattle(String name) {
		List <MatchDTO> PtoM = MatchDAO.playerToMatch(name);

		if(PtoM != null && !PtoM.isEmpty()) {
			for(MatchDTO match : PtoM) {
				System.out.printf("%s 와 %s의 경기는 %s 세트까지 경기 끝에 %s 이(가) 승리하였습니다.\n경기스코어: %s\n",match.getPlayer1(), match.getPlayer2(),match.getSetOfgame(), match.getWinner(), match.getGameScore());
				System.out.println("=============================");
				System.out.printf("   🎾%s   vs   %s🎾\n",match.getPlayer1(),match.getPlayer2());
				System.out.println("-----------------------------");
				System.out.printf("  %s 세트까지 가는 경기 끝에\n🏅%s🏅이(가) 승리하였습니다.\n",match.getSetOfgame(),match.getWinner());
				System.out.println("         게임 스코어");
				System.out.printf("      %10s\n",match.getGameScore());
				System.out.println("=============================");
			}
			} else {
				System.out.println("선수 목록에 해당 이름이 없습니다.");
		}
	}

	// 랭킹순위==============================================
	public static void rankListGet() {
		// 플레이어 DTO를 RankDAO 랭킹리스트로 담기
		List<PlayerDTO> rankList = PlayerDAO.getRankByPlayer();

		// 순위정렬하기
		rankList.sort((o1, o2) -> {
            // 승률순서대로 (높은 순서대로)
            if (o1.getOddsOfWinning() > o2.getOddsOfWinning()) {
                return -1;
            } else if (o1.getOddsOfWinning() < o2.getOddsOfWinning()) {
                return 1;
            } else if (o1.getOddsOfWinning() == o1.getOddsOfWinning()) {
                // 승률이 같다면 > 승 많은 순서대로 (높은 순서대로)
                if (o1.getWins() > o2.getWins()) {
                    return -1;
                } else if (o1.getWins() < o2.getWins()) {
                    return 1;
                } else {
                    // 승 갯수가 같다면 > 이름순서대로
                    if (o1.getName() == null && o2.getName() == null) {
                        return 0;
                    } else if (o1.getName() == null) {
                        return -1;
                    } else if (o2.getName() == null) {
                        return 1;
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            }
            return 0;
        });

		if (!rankList.isEmpty()) {
			int countRank = 1;
			String emoji = "";
			System.out.println("[RANK]\t[PLAYER]\t[WIN]\t[LOSE]\t[Winning rate(%)]");
			for (PlayerDTO player : rankList) {
				if (countRank == 1) {
					emoji = "🥇";
				} else if (countRank == 2) {
					emoji = "🥈";
				} else if (countRank == 3) {
					emoji = "🥉";
				} else {
					emoji = "  ";
				}
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.printf("%s%d위\t%4s\t%11d\t%4d\t%8.0f", emoji, countRank, player.getName(), player.getWins(),
						player.getLoses(), player.getOddsOfWinning());
				System.out.println("%");

				// 최대 랭킹순위 (10위)
				if (countRank < 10) {
					countRank++;
				} else {
					break;
				}
			}
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println();
		} else {
			System.out.println("아직 해당 정보가 없습니다.");
		}
	}
}
