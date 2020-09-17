package robo.event;

import robo.event.PositionEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PasteFromBuffer implements PositionEvent {

    private Robot robot;

    public PasteFromBuffer(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doInput() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
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