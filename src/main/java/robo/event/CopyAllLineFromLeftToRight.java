package robo.event;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CopyAllLineFromLeftToRight implements PositionEvent {

    private Robot robot;

    public CopyAllLineFromLeftToRight(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doInput() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_END);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_END);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    @Override
    public boolean needMove() {
        return false;
    }

    @Override
    public int[] getCoordinates() {
        throw new RuntimeException("Action class "+getClass().getSimpleName()+" don't need coordinates for moving");
    }
}
