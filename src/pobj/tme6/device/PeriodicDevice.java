package pobj.tme6.device;

public abstract class PeriodicDevice implements IDevice {

    private int period;
    private int counter;

    public PeriodicDevice(int period) {
        this.period = period;
        this.counter = period;
    }

    public abstract void action();

    @Override
    public void tick(int time) {
        counter -= time;
        if (counter <= 0) {
            action();
            counter += period;
        }
    }
}
