package app.level;

public enum Direction {
    LEFT("west"), RIGHT("east"), UP("north"), DOWN("south");

    private String compassDirection;

    Direction(String compassDirection) {
        this.compassDirection = compassDirection;
    }

    public String getCompassDirection() {
        return compassDirection;
    }
}
