package robo.event;

public class SleepForTime implements PositionEvent {

    private long sleepTime;

    public SleepForTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void doInput() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean needMove() {
        return false;
    }

    @Override
    public int[] getCoordinates() {
        throw new RuntimeException("Action class "+getClass().getSimpleName()+" don't need coordinates for moving");
    }

    public long getSleepTime() {
        return sleepTime;
    }
}
