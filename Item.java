import java.util.*;

/**
 * This is the super class to define an item object
 * @author Josh/Mike
 * @version 1.0
 */
public abstract class Item{

    protected int shortcutKey;
    protected int spawnTime;

    /**
     * constructor
     * @param none
     */
    public Item(){

    }

}

//lethal items - kills rats
class LethalItem extends Item{

    //private int scKey = ;//
    //private int spawnTime = ;//get from level file

    public LethalItem(){

    }

}

class Bomb extends LethalItem{
    /**bomberman bombs until grass
     * shows countdown to explosrion 4s - 1s
    */
}

class Gas extends LethalItem{
    //slowly expands, rats can survive for certain amount of time
}

class Poison extends LethalItem{
    //insta kill

}

//other items
class NoEntry extends Item{
    //blocks path
}

class SexChangeM extends Item{
    //change gender to F
}

class SexChangeF extends Item{
    //change gender to M
}

class Sterilisation extends Item{
    //works within small radius
    //rats cannot reproduce
}