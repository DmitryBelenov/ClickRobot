package robo.event;

import robo.utils.exception.NoCoordinatesForMoving;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CopyAllLineFromRightToLeft implements PositionEvent {

    private Robot robot;

    public CopyAllLineFromRightToLeft(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doInput() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_HOME);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_HOME);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_SHIFT);
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
