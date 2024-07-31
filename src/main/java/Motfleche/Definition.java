package Motfleche;

public class Definition {
    private String text;
    private Direction direction;

    public Definition(String text, Direction direction) {
        this.text = text;
        this.direction = direction;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}