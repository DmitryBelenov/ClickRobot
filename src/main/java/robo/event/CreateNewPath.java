package robo.event;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CreateNewPath implements PositionEvent {

    private Robot robot;

    public CreateNewPath(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doInput() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_N);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_N);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.delay(100);
        PressKeyBoardKeyAtPosition pressKeyBoardKeyAtPosition = new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_ENTER);
        pressKeyBoardKeyAtPosition.doInput();
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
