package robo;

import org.apache.commons.io.FileUtils;
import robo.event.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class ClickRobot {

    private Robot robot;

    public ClickRobot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void showProgramLogo(){
        System.out.println("  ______  _         __   ______  _    __\n" +
                           " |  ___/ | |       |_ | |  ___/ | |__/ /\n" +
                           " | |     | |       | |  | |     |  __ <\n" +
                           " | |___  | |____   | |  | |___  | |  \\ \\\n" +
                           " |_____\\ |______\\  |_|  |_____\\ |_|   \\_\\\n" +
                           " _____    _____   _____    _____   _______\n" +
                           "|  __ \\  / ___ \\ |  __ \\  / ___ \\ |__   __|\n" +
                           "| |__| || /   \\ || |__| || /   \\ |   | |\n" +
                           "| | \\ \\ | \\___/ || |__| || \\___/ |   | |\n" +
                           "|_|  \\_\\ \\_____/ |_____/  \\_____/    |_|"+"\n");
    }

    public void startRobot(){
        showProgramLogo();
        Scanner scanner;
        boolean showBeginCMD = true;
        while (showBeginCMD) {
            System.out.print("-> ");
            scanner = new Scanner(System.in);
            String myInput = scanner.nextLine();
            switch (myInput) {
                case "start":
                    makeRobotScenario();
                    showBeginCMD = false;
                    break;
                case "read":
                    System.out.println("File name:");
                    Scanner sc = new Scanner(System.in);
                    String fileName = sc.nextLine();
                    readScenarioFromFile(fileName);
                    break;
                case "exit":
                    showBeginCMD = false;
                    break;
                default:
                    System.out.println("No such command");
                    break;
            }
        }
    }

    private void makeRobotScenario(){
        List<PositionEvent> events = new ArrayList<>();
        Scanner scanner;
        System.out.println("Start writing commands");
        boolean read = true;
        while (read) {
            System.out.print("-> ");
            scanner = new Scanner(System.in);
            String myInput = scanner.nextLine();
            if (myInput.equals("end")) {
                read = false;
            } else {
                fillActionList(events, myInput);
            }
        }

        System.out.println("Total events: " + events.size());

        Scanner scannerRepeat;
        boolean repeat = true;
        while (repeat) {
            System.out.print("r - repeat\ns - save to file\n-> ");
            scannerRepeat = new Scanner(System.in);
            String userInput = scannerRepeat.nextLine();
            if (userInput.equalsIgnoreCase("r")) {
                runListCommandStream(events);
            } else if (userInput.equalsIgnoreCase("s")){
                saveScenarioToFile(events);
                repeat = false;
            } else if (userInput.equalsIgnoreCase("exit")) {
                repeat = false;
            } else {
                System.out.println("No such command");
            }
        }
    }

    private void runListCommandStream(List<PositionEvent> events){
        System.out.println("Script is executed..");
        long startRobotWork = System.currentTimeMillis();
        for (PositionEvent positionEvent : events) {
            robot.delay(300);
            positionEvent.doInput();
        }
        long endRobotWork = System.currentTimeMillis();
        System.out.println("Script complete. Time: "+(endRobotWork-startRobotWork)/1000+" sec.");
    }

    private void fillActionList(List<PositionEvent> events, String actionLabel) {
        int[] c = new int[2];
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        c[0] = (int) mouseLocation.getX();
        c[1] = (int) mouseLocation.getY();

        commandFactoryToFillEventsList(actionLabel, events, c);
    }

    private void commandFactoryToFillEventsList(String actionLabel, List<PositionEvent> events, int[] c){
        /* фабрика комманд */
        if (actionLabel.equals("r")) {
            events.add(new MouseMoveAndRightClick(robot, c));
        } else if (actionLabel.equals("l")) {
            events.add(new MouseMoveAndLeftClick(robot, c));
        } else if (actionLabel.equals("dc")) {
            events.add(new MoveAndDoubleClick(robot, c));

        } else if (actionLabel.equals("down")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_DOWN));
        } else if (actionLabel.equals("up")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_UP));
        } else if (actionLabel.equals("left")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_LEFT));
        } else if (actionLabel.equals("right")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_RIGHT));
        } else if (actionLabel.equals("esc")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_ESCAPE));
        } else if (actionLabel.equals("delete")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_DELETE));
        } else if (actionLabel.equals("enter")) {
            events.add(new PressKeyBoardKeyAtPosition(robot, KeyEvent.VK_ENTER));

        } else if (actionLabel.startsWith("text:")) {
            String textToWrite = actionLabel.substring(5);
            events.add(new WriteTextAtPosition(robot, textToWrite, c));

        } else if (actionLabel.equals("copy")) {
            events.add(new CopyToBuffer(robot));
        } else if (actionLabel.equals("paste")) {
            events.add(new PasteFromBuffer(robot));
        } else if (actionLabel.equals("all")) {
            events.add(new SelectAll(robot));
        } else if (actionLabel.equals("lineR")) {
            events.add(new CopyAllLineFromRightToLeft(robot));
        } else if (actionLabel.equals("lineL")) {
            events.add(new CopyAllLineFromLeftToRight(robot));

        } else if (actionLabel.equals("full")) {
            events.add(new ActiveWindowToFullScreen(robot));

        } else if (actionLabel.equals("path")) {
            events.add(new CreateNewPath(robot));

        } else if (actionLabel.startsWith("sleep:")) {
            long timeForSleep = 3000; // 3 секунды сна по умолчанию
            try {
                timeForSleep = Long.parseLong(actionLabel.substring(6));
            } catch (Exception e){
                System.out.println("Unable to parse sleep time. Set 3000 ms as default");
            }
            events.add(new SleepForTime(timeForSleep));

        } else {
            System.out.println("No such command");
        }
    }

    private void saveScenarioToFile(List<PositionEvent> events){
        System.out.println("File name:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        for (PositionEvent positionEvent : events){
            if (positionEvent.needMove()){
                int[] c = positionEvent.getCoordinates();
                sb.append(c[0]).append("x").append(c[1]);
            } else {
                sb.append("motionless");
            }
            sb.append("=");

            if (positionEvent instanceof MouseMoveAndLeftClick){
                sb.append("l");
            } else if (positionEvent instanceof MouseMoveAndRightClick){
                sb.append("r");
            } else if (positionEvent instanceof MoveAndDoubleClick){
                sb.append("dc");
            } else if (positionEvent instanceof PressKeyBoardKeyAtPosition){
                PressKeyBoardKeyAtPosition pressKeyBoardKeyAtPosition = (PressKeyBoardKeyAtPosition) positionEvent;
                sb.append("key:").append(pressKeyBoardKeyAtPosition.getKeyNum());
            } else if (positionEvent instanceof WriteTextAtPosition){
                WriteTextAtPosition writeTextAtPosition = (WriteTextAtPosition) positionEvent;
                sb.append("text:").append(writeTextAtPosition.getTextToWrite());
            } else if (positionEvent instanceof CopyToBuffer){
                sb.append("copy");
            } else if (positionEvent instanceof PasteFromBuffer){
                sb.append("paste");
            } else if (positionEvent instanceof SelectAll){
                sb.append("all");
            } else if (positionEvent instanceof CopyAllLineFromRightToLeft){
                sb.append("lineR");
            } else if (positionEvent instanceof CopyAllLineFromLeftToRight){
                sb.append("lineL");
            } else if (positionEvent instanceof SleepForTime){
                SleepForTime sleepForTime = (SleepForTime) positionEvent;
                sb.append("sleep:").append(sleepForTime.getSleepTime());
            } else if (positionEvent instanceof ActiveWindowToFullScreen){
                sb.append("full");
            } else if (positionEvent instanceof CreateNewPath){
                sb.append("path");
            }
            sb.append("\n");
        }

        File file = new File(System.getProperty("user.home")+"/AppData/Local/robot/scenarios/"+fileName+".rs");
        try {
            FileUtils.writeStringToFile(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File saved");
    }

    private void readScenarioFromFile(String fileName){
        File file = new File(System.getProperty("user.home")+"/AppData/Local/robot/scenarios/"+(fileName.endsWith(".rs") ? fileName : fileName+".rs"));
        try {
            List<String> commandLines = FileUtils.readLines(file, "UTF-8");
            List<PositionEvent> events = new ArrayList<>();

            for (String line : commandLines) {
                String[] commArray = line.split("=");
                int [] c = null;
                if (commArray[0].contains("x")) {
                    String[] coordinatesArray = commArray[0].split("x");
                    c = new int[]{Integer.parseInt(coordinatesArray[0]),
                                    Integer.parseInt(coordinatesArray[1])};
                }

                PositionEvent positionEvent;
                String actionLine = commArray[1];
                if (actionLine.contains("key")){
                    String[] keyNumArr = actionLine.split(":");
                    int keyNum = Integer.parseInt(keyNumArr[1]);
                    positionEvent = new PressKeyBoardKeyAtPosition(robot, keyNum);
                    events.add(positionEvent);
                } else {
                    commandFactoryToFillEventsList(actionLine, events, c);
                }
            }

            runListCommandStream(events);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

