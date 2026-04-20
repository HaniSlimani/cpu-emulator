package pobj.tme6.device;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class Screen extends PeriodicDevice implements IMemory {

    private RAM video;

    public Screen() {
        super(100);
        video = new RAM(10, 32);
    }

    @Override
    public int size() {
        return video.size();
    }

    @Override
    public int read(int addr) {
        return video.read(addr);
    }

    @Override
    public void write(int addr, int val) {
        video.write(addr, val);
    }

    @Override
    public void action() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < video.size(); i++) {
            sb.append((char) video.read(i));
        }
        System.out.println(sb.toString());
    }
}
