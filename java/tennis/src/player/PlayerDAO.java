package player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;

public class PlayerDAO {

    // 플레이어 추가하기(이름)
    public static void add(PlayerDTO playerdto) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter((Constants.PLAYER_FILE_PATH), true));
            String line = String.format("%d,%s,%d,%d,%.0f\r\n", playerdto.getPlayerId(), playerdto.getName(), playerdto.getWins(), playerdto.getLoses(), playerdto.getOddsOfWinning());
            writer.write(line);
            writer.close();
        } catch (Exception e) {
            System.out.println("PlayerDAO.add");
            e.printStackTrace();
        }
    }

    // 플레이어 확인하기 (이름)
    public static PlayerDTO nameGetDTO(String name) {
        try {
            BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
            String line = null;
            while ((line = playerReader.readLine()) != null) {
                String[] temp = line.split(",");
                if (temp[1].equals(name)) {
                    PlayerDTO playerdto = new PlayerDTO();
                    playerdto.setPlayerId(Integer.parseInt(temp[0]));
                    playerdto.setName(name);
                    playerdto.setWins(Integer.parseInt(temp[2]));
                    playerdto.setLoses(Integer.parseInt(temp[3]));
                    return playerdto;
                }
            }
            playerReader.close();
        } catch (Exception e) {
            System.out.println("PlayerDAO.getName");
            e.printStackTrace();
        }
        return null;
    }

    // 플레이어 저장 개수 확인
    public static int getTotalPlayerNumber() {
        int count = 0;
        try {
            BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
            String line = "";

            while ((line = playerReader.readLine()) != null) {
                count++;
            }
            playerReader.close();
        } catch (Exception e) {
            System.out.println("PlayerDAO.getTotalPlayerNumber");
            e.printStackTrace();
        }
        return count;
    }

    // 플레이어 확인하기(ID)
    public static PlayerDTO getById(int id) {
        try {
            BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
            String line = null;
            while ((line = playerReader.readLine()) != null) {
                if (line.startsWith(String.valueOf(id))) {
                    String[] temp = line.split(",");
                    PlayerDTO playerDTO = new PlayerDTO();
                    playerDTO.setPlayerId(id);
                    playerDTO.setName(temp[1]);
                    playerDTO.setWins(Integer.parseInt(temp[2]));
                    playerDTO.setLoses(Integer.parseInt(temp[3]));
                    return playerDTO;
                }
            }
            playerReader.close();
        } catch (Exception e) {
            System.out.println("PlayerDAO.getId");
            e.printStackTrace();
        }
        return null;
    }

    // 플레이어 수정하기(ID)
    public static void edit(PlayerDTO playerDTO) {
        try {
            // 기존 점수 > 수정
            BufferedReader playerReader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
            String line = null;
            String temp = ""; // 누적변수

            while ((line = playerReader.readLine()) != null) {
                int playerId = playerDTO.getPlayerId();

                if (!line.startsWith(String.valueOf(playerId))) { // 덮어쓰기
                    temp += line + "\r\n"; // 읽어오면 엔터사라짐
                } else {
                    // 기존 사람이라면 그대로 누적
                    int wins = playerDTO.getWins();
                    int loses = playerDTO.getLoses();
                    double oddsOfWinning = (wins / (wins + (double) loses)) * 100;
                    temp += String.format("%d,%s,%d,%d,%f\r\n", playerId, playerDTO.getName(), wins, loses, oddsOfWinning);
                }
            }
            playerReader.close();

            // 수정된 내용 > 덮어쓰기
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.PLAYER_FILE_PATH));
            writer.write(temp);
            writer.close();
        } catch (Exception e) {
            System.out.println("PlayerDAO.edit");
            e.printStackTrace();
        }
    }

    // RankList에 PlayerDTO 담기
    public static List<PlayerDTO> getRankByPlayer() {
        ArrayList<PlayerDTO> rankList = new ArrayList<PlayerDTO>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.PLAYER_FILE_PATH));
            String line;
            int playerId;
            String name;
            int wins;
            int loses;
            double oddsOfWinning;
            String[] temp;

            while ((line = reader.readLine()) != null) {
                temp = line.split(",");
                playerId = Integer.parseInt(temp[0]);
                name = temp[1];
                wins = Integer.parseInt(temp[2]);
                loses = Integer.parseInt(temp[3]);
                oddsOfWinning =  Double.parseDouble(temp[4]);
                rankList.add(new PlayerDTO(playerId, name, wins, loses, oddsOfWinning));
            }

            reader.close();
            return rankList;
        } catch (Exception e) {
            System.out.println("PlayerDAO.getRankByPlayer");
            e.printStackTrace();
        }
        return null;
    }
}

