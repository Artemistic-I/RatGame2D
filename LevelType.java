package constants;

	import java.io.Serializable;

	/**
	 * Defines a Level
	 *
	 * @author Walid Mohamed
	 * @version 1.0
	 */
	public enum LevelType implements Serializable {
	    BIG("source/resources/file/Big", "Big"),
	    MEDIUM("source/resources/file/Medium", "Medium"),
	    SMALL("source/resources/file/Small", "Small"),
	    WIN("source/resources/file/--{WIN}--", "win_demo");
	    final String PATH;
	    final String NAME;

	    LevelType(String path, String name) {
	        this.PATH = path;
	        this.NAME = name;
	    }

	    /**
	     * Retrieves the path of the level file
	     *
	     * @return File Path
	     */
	    public String getPath() {
	        return PATH;
	    }

	    /**
	     * Retrieves the name of the level file
	     *
	     * @return Name of level
	     */
	    public String getName() {
	        return NAME;
	    }

	    /**
	     * Converts the level to String
	     *
	     * @return level as String
	     */
	    @Override
	    public String toString() {
	        return NAME;
	    }
	}
