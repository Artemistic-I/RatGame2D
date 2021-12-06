
/**
 * Class to model a score for scoreboard
 * 
 * @author Artem Iakovlev
 */
public class Score {
    
    private PlayerProfile player;
    private int scoreNum;

    /**
     * Creates a score.
     * @param player player whose score is modeled
     * @param scoreNum score value/number
     */
    public Score(PlayerProfile player, int scoreNum) {
        this.player = player;
        this.scoreNum = scoreNum;
    }

    /**
     * 
     * @return returns playerProfile
     */
    public PlayerProfile getPlayer() {
        return player;
    }
    /**
     * Gets score value/number
     * @return score number
     */
    public int getScoreNum() {
        return scoreNum;
    }

    /**
     * outputs information in string format
     */
    public String toString() {
        return player.getPlayerUsername() + " - " + scoreNum;
    }
}
