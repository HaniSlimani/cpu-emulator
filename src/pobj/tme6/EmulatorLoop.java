package pobj.tme6;

import java.util.ArrayList;
import java.util.List;

import pobj.tme6.cpu.ICPU;
import pobj.tme6.device.IDevice;

public class EmulatorLoop implements IEmulatorLoop {

    private ICPU cpu;
    private List<IDevice> devices;

    // Constructeur : initialise le CPU et la liste de périphériques
    public EmulatorLoop(ICPU cpu) {
        this.cpu = cpu;
        this.devices = new ArrayList<>();
    }

    // Ajout d'un périphérique
    @Override
    public void addDevice(IDevice d) {
        devices.add(d);
    }

    // Boucle d'émulation
    @Override
    public void run(int time) {
        int elapsed = 0;
        while (elapsed <= time) {
            int t = cpu.execute(); // exécute une instruction et récupère le temps écoulé
            elapsed += t;

            // Notifie tous les périphériques
            for (IDevice d : devices) {
                d.tick(t);
            }
        }
    }
}
