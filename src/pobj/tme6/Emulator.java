package pobj.tme6;

import pobj.tme6.cpu.CPU;
import pobj.tme6.device.Screen;
import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;
import pobj.tme6.memory.ROM;

public class Emulator {

    private CPU cpu;
    private EmulatorLoop loop;

    public Emulator(int[] romContent) {
        // Création des mémoires
        ROM rom = new ROM(romContent);       // ROM de 100 mots, adresses 0-99
        RAM ram = new RAM(100, 0); 			// RAM de 100 mots initialisés à 0
        Screen screen = new Screen();        // Mémoire vidéo de 10 mots, adresses 200-209

        // Décodeur d'adresses (taille totale : 210 mots)
        AddressDecoder decoder = new AddressDecoder(210);
        decoder.add(new MemorySlot(0, rom));
        decoder.add(new MemorySlot(100, ram));
        decoder.add(new MemorySlot(200, screen));

        // Création du CPU avec le décodeur
        cpu = new CPU(decoder);

        // Création de la boucle d'émulation
        loop = new EmulatorLoop(cpu);

        // Enregistrement de l'écran comme périphérique
        loop.addDevice(screen);
    }

    // Getter pour le CPU
    public CPU getCPU() {
        return cpu;
    }

    // Lance l'émulation pendant time unités
    public void run(int time) {
        cpu.reset();
        loop.run(time);
    }
}
