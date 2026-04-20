package pobj.tme6.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clock implements IDevice {
    private int date;
    private List<Alarm> alarms;

    public Clock() {
        this.date = 0;
        this.alarms = new ArrayList<>();
    }

    public void addAlarm(Alarm a) {
        alarms.add(a);
        Collections.sort(alarms); // trie en ordre décroissant
    }

    @Override
    public void tick(int time) {
        date += time;

        // Parcours de la liste depuis la fin pour exécuter les alarmes dues
        for (int i = alarms.size() - 1; i >= 0; i--) {
            Alarm a = alarms.get(i);
            if (a.getDate() <= date) {
                a.action();
                alarms.remove(i);
            } else {
                // comme la liste est triée par ordre décroissant, on peut s'arrêter
                break;
            }
        }
    }

    public int getDate() {
        return date;
    }
}
