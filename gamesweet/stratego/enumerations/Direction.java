package gamesweet.stratego.enumerations;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

	public static Direction reverse(Direction direction) {
		switch(direction) {
			case NORTH: return SOUTH;
			case SOUTH: return NORTH;
			case EAST:  return WEST;
			case WEST:  return EAST;
			default:    return null;
		}
	}
}