package robo.event;

public interface PositionEvent {
    void doInput();
    boolean needMove();
    int[] getCoordinates();
}
