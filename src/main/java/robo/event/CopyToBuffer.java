package robo.event;

import robo.utils.exception.NoCoordinatesForMoving;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CopyToBuffer implements PositionEvent {

    private Robot robot;

    public CopyToBuffer(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doInput() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_C);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    @Override
    public boolean needMove() {
        return false;
    }

    @Override
    public int[] getCoordinates() {
        throw new NoCoordinatesForMoving("Action class "+getClass().getSimpleName()+" don't need coordinates for moving");
    }
}
