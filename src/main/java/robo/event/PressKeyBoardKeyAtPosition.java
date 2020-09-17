package robo.event;

import java.awt.*;

public class PressKeyBoardKeyAtPosition implements PositionEvent {

    private Robot robot;
    private int keyNum;

    public PressKeyBoardKeyAtPosition(Robot robot, int keyNum) {
        this.robot = robot;
        this.keyNum = keyNum;
    }

    @Override
    public void doInput() {
        robot.keyPress(keyNum);
        robot.delay(200);
        robot.keyRelease(keyNum);
    }

    @Override
    public boolean needMove() {
        return false;
    }

    @Override
    public int[] getCoordinates() {
        throw new RuntimeException("Action class "+getClass().getSimpleName()+" don't need coordinates for moving");
    }

    public int getKeyNum() {
        return keyNum;
    }
}
