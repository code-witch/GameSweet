package gamesweet.stratego.interfaces;

import gamesweet.stratego.enumerations.Direction;

public interface Movable {
    /**
     * @param direction = The direction of movement
     * @param amount = The amount of tiles to move
     */
    public void move(Direction directioon, int amount);

    /**
     * @param direction = The direction to attack
     */
    public void attack(Direction direction);

}