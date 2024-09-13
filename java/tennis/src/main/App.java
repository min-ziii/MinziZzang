package main;

import java.util.Scanner;

import match.MatchView;
import player.PlayerView;
import util.AnimationView;
import util.Util;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnimationView.entryTitle("THE/TENNIS/GAME", 200, 20);
        System.out.println("=".repeat(40));

        mainMenu(scanner);
        scanner.close();
    }

    public static void mainMenu(Scanner scanner) {
        boolean firstEnter = true;

        while (true) {
            if (!firstEnter) {
                for (String line : tennisBallArt) {
                    System.out.println(line);
                }
                Util.printTitle("🎾 테니스 게임 🎾");
            }

            firstEnter = false;

            System.out.println("1. 게임 시작 🏸");
            System.out.println("2. 플레이어 생성 🤾‍");
            System.out.println("3. 리더보드 🏅");
            System.out.println("4. 종료 🚪");
            System.out.println();
            System.out.print("➡ 옵션을 선택하세요: ");

            String choice = "";

            while (choice.isEmpty()) {
                choice = scanner.nextLine();
                if (choice.equals("1")) {
                    MatchView.matchMenu(scanner);
                } else if (choice.equals("2")) {
                    PlayerView.createPlayer(scanner);
                } else if (choice.equals("3")) {
                    PlayerView.leaderBoardMenu(scanner);
                } else if (choice.equals("4")) {
                    System.out.println();
                    System.out.println("게임을 종료합니다.");
                    return;
                } else {
                    System.out.println("🚨 잘못된 옵션입니다. 올바른 옵션을 선택해주세요!");
                }
            }
        }
    }

    private static final String[] tennisBallArt =
            {"\n\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠠⠀⠠⠀⠄⠠⠀⡀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠐⢀⠁⠠⠐⢀⠁⠐⡀⠂⡐⢀⠐⠠⠐⠀⢂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⢀⠐⢀⠈⠠⠀⠡⠐⢀⠂⠡⠐⡀⡂⡐⠨⢐⠈⢌⠠⡈⡐⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⡀⠂⠠⠐⠀⠄⢁⠈⠄⡈⠄⠠⠁⡂⠄⠂⢄⢁⠂⠌⠄⡂⡐⠠⢐⠠⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠄⠐⢈⠠⠈⠠⢈⠀⡂⢐⠠⠈⠄⡁⡂⠌⢌⢐⠄⠅⡅⠕⡐⡨⠨⡐⡐⠠⠂⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⡐⢈⠐⢀⠐⡈⠄⢂⠐⡀⢂⢂⠡⢁⢂⠢⢑⠰⡐⠅⢕⠨⡨⢂⢊⠢⡂⠪⠨⢐⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠠⠐⡀⢐⠀⡂⣶⡮⢶⡷⢾⣷⢰⢾⢰⣿⣻⡆⢕⢐⢅⢅⠕⢌⢢⢑⠌⡌⢌⠌⠔⡐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠄⠡⠐⡀⢂⠐⣼⡧⣻⡯⡸⣿⢺⣿⢨⣷⡟⣾⣿⣔⣿⢻⣷⢿⣷⢿⣎⠢⡑⠨⢂⢂⠡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠨⢀⠡⠐⡀⢂⢽⣧⡿⣯⣾⢟⢸⣿⠾⣿⡾⢿⢾⠯⡻⡷⡟⡺⡿⢼⣿⢔⠨⠨⢂⠂⡂⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠡⠐⡀⠅⡐⡀⡂⠌⡊⠌⡂⢅⢂⠢⡑⢔⣵⡧⠣⡑⡑⢌⠢⡂⡪⢐⢐⠔⡡⠡⢑⠈⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠨⢀⠂⡂⢐⢀⠂⠅⡂⠅⡂⡂⡢⢑⠨⡨⢽⣏⠪⡐⢌⠢⢑⠐⠌⡂⠅⡊⢄⠑⠄⠅⠡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠐⡀⢂⢐⠐⡀⡊⡐⠄⠅⡂⡢⡈⠢⡁⡊⡺⢇⠪⡐⡐⢅⠢⠡⡑⡈⠢⠨⠠⠡⠡⢁⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠐⡀⢂⠂⡂⢂⢐⢁⠪⢐⢐⢈⢂⠆⡂⡪⢐⠰⢐⠌⡢⠨⡊⠔⡨⠨⡊⠌⠌⠌⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠐⢀⠂⡂⢂⠂⡂⠌⠔⡐⡐⠄⢕⠐⢌⢐⢅⢑⠌⡂⢅⠢⡑⡐⢅⠢⠡⠡⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠐⠐⠄⢅⠢⠡⡑⡐⡌⢌⢢⢡⢑⢔⢔⢢⢑⢌⢆⢕⢌⢢⢑⠌⠌⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠨⠐⢌⠪⠨⡂⢎⢜⠰⡡⢣⢱⠸⡘⡜⢜⢌⢆⢣⠱⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠑⠕⢕⢕⣜⢜⣜⢼⡸⣜⢮⣪⢣⠣⠃⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠁⠉⠉⠈⠁⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                   "};
}
