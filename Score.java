
/**
 * Class to model a score for scoreboard
 * 
 * @author Artem Iakovlev
 */
public class Score {
    
    private PlayerProfile player;
    private int scoreNum;

    public Score(PlayerProfile player, int scoreNum) {
        this.player = player;
        this.scoreNum = scoreNum;
    }
    public PlayerProfile getPlayer() {
        return player;
    }

    public int getScoreNum() {
        return scoreNum;
    }

    public String toString() {
        return player.getPlayerUsername() + " - " + scoreNum;
    }
}
