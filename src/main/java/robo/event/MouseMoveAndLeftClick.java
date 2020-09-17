package robo.event;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseMoveAndLeftClick implements PositionEvent {

    private Robot robot;
    private int[] coordinates;

    public MouseMoveAndLeftClick(Robot robot, int[] coordinates) {
        this.robot = robot;
        this.coordinates = coordinates;
    }

    public void doInput() {
        robot.mouseMove(coordinates[0], coordinates[1]);

        robot.delay(300);

        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Override
    public boolean needMove() {
        return true;
    }

    @Override
    public int[] getCoordinates() {
        return coordinates;
    }
}
