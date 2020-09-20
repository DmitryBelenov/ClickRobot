package robo.event;

import java.awt.*;
import java.awt.event.InputEvent;

public class WriteTextAtPosition implements PositionEvent {

    private Robot robot;
    private String textToWrite;
    private int[] coordinates;

    public WriteTextAtPosition(Robot robot, String textToWrite, int[] coordinates) {
        this.robot = robot;
        this.textToWrite = textToWrite;
        this.coordinates = coordinates;
    }

    public void doInput() {
        robot.mouseMove(coordinates[0], coordinates[1]);

        robot.delay(300);

        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        String textToWrite = getTextToWrite();
        for (int i = 0; i < textToWrite.length(); i++){
            char c = textToWrite.charAt(i);
            int keyToPressNum = RobotUtils.keyboardMap.get(c);

            robot.delay(100);
            robot.keyPress(keyToPressNum);
            robot.keyRelease(keyToPressNum);
        }
    }

    @Override
    public boolean needMove() {
        return true;
    }

    @Override
    public int[] getCoordinates() {
        return coordinates;
    }

    public String getTextToWrite() {
        return textToWrite;
    }
}
