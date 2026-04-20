package pobj.tme6.memory;

public class RAM implements ICopyableMemory {

    private int[] mem;

    public RAM(int size, int init) {
        mem = new int[size];
        for (int i = 0; i < size; i++) {
            mem[i] = init;
        }
    }

    @Override
    public int size() {
        return mem.length;
    }

    @Override
    public int read(int addr) {
        return mem[addr];
    }

    @Override
    public void write(int addr, int val) {
        mem[addr] = val;
    }

    @Override
    public ICopyableMemory copy() {
        RAM r = new RAM(mem.length, 0);
        for (int i = 0; i < mem.length; i++) {
            r.mem[i] = this.mem[i];
        }
        return r;
    }
}
