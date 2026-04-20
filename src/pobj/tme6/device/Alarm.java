package pobj.tme6.device;

public class Alarm implements Comparable<Alarm> {
    private int date;
    private IAction action;

    public Alarm(int date, IAction action) {
        this.date = date;
        this.action = action;
    }

    public int getDate() {
        return date;
    }

    public void action() {
        action.action();
    }

    @Override
    public int compareTo(Alarm x) {
        if (x.date > this.date) return 1;
        else if (x.date < this.date) return -1;
        else return 0;
    }
}
