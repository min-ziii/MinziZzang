package player;

public class PlayerService {
    // 플레이어 생성
    public static void createPlayer (String name) {
        PlayerDAO.add(new PlayerDTO(name, 0, 0, 0));
    }

    // 플레이어 이름으로 확인
    public static PlayerDTO getByName(String name) {
        return PlayerDAO.nameGetDTO(name);
    }

    // 플레이어 아이디로 확인
    public static PlayerDTO getById(int id) {
        return PlayerDAO.getById(id);
    }

    // 플레이어 승리 수정
    public static void increaseWin(int playerId) {
        PlayerDTO playerDTO = PlayerDAO.getById(playerId);

        if (playerDTO != null) {
            playerDTO.setWins(playerDTO.getWins() + 1);
        }

        PlayerDAO.edit(playerDTO);
    }

    // 플레이어 패배 수정
    public static void increaseLose(int playerId) {
        PlayerDTO playerDTO = PlayerDAO.getById(playerId);

        if (playerDTO != null) {
            playerDTO.setLoses(playerDTO.getLoses() + 1);
        }

        PlayerDAO.edit(playerDTO);
    }
}
