package player;

import com.github.lalyos.jfiglet.FigletFont;
import leaderBoard.LeaderBoardService;
import util.Util;

import java.util.Scanner;

public class PlayerView {
	public static void leaderBoardMenu(Scanner scanner) {
		while (true) {
			Util.printTitle("Leader Board");
			System.out.println("1. 선수랭킹");
			System.out.println("2. 선수별 상대전적");
			System.out.println("3. 뒤로가기");
			System.out.println();
			System.out.print("➡ 옵션을 선택하세요: ");

			String choice = scanner.nextLine();

			if (choice.equals("1")) {
				showPlayerRanking(scanner);
			} else if (choice.equals("2")) {
				showVerseRecords(scanner);
			} else if (choice.equals("3")) {
				break;
			} else {
				System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
			}
		}
	}

	public static void createPlayer(Scanner scanner) {
		Util.printTitle("💁  " + "새로운 플레이어 생성" + "  🙋");
		String name = "";
		while (name.isEmpty()) {

			System.out.print("이름을 입력하세요: ");
			name = scanner.nextLine();
			PlayerDTO playerDTO = PlayerService.getByName(name);
			if (playerDTO == null) {
				PlayerService.createPlayer(name);
				System.out.println();
				System.out.println("💫새로운 플레이어를 생성하였습니다💫");
			} else {
				System.out.println();
				System.out.println("이미 생성된 플레이어입니다‼");
				System.out.println();
				name = "";
			}
		}
	}

	private static void showPlayerRanking(Scanner scanner) {
		System.out.println();
		System.out.println("============================================================");
		try {
			String asciiArt1 = FigletFont.convertOneLine("RANKING");
			System.out.print(asciiArt1);
		} catch (Exception e) {
			System.out.println("PlayerView.showPlayerRanking");
			e.printStackTrace();
		}
		System.out.println("============================================================");

		LeaderBoardService.rankListGet();

		System.out.print("아무 키나 입력하여 뒤로가기: ");
		scanner.nextLine();

		// System.out.println("0. 뒤로가기");
		// System.out.print("옵션을 선택하세요: ");
		// String choice = scanner.nextLine();
		// if (!choice.equals("0")) {
		// System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
		// }
	}

	private static void showVerseRecords(Scanner scanner) {
		Util.printTitle("선수별 상대전적");

		System.out.print("선수 이름을 입력하시오: ");
		String name = scanner.nextLine();
		LeaderBoardService.relativeBattle(name);

		System.out.print("아무 키나 입력하여 뒤로가기: ");
		scanner.nextLine();
		// System.out.println("0. 뒤로가기");
		// System.out.print("옵션을 선택하세요: ");
		// String choice = scanner.nextLine();
		// if (!choice.equals("0")) {
		// System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
		// }
	}
}
