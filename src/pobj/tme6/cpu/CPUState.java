package pobj.tme6.cpu;

import pobj.tme6.memory.IMemory;

public class CPUState implements ICPUState {

    private IMemory memory;
    private int PC;
    private int A;

    public CPUState(IMemory memory) {
        this.memory = memory;
        this.PC = 0;
        this.A = 0;
    }

    @Override
    public int getPC() {
        return PC;
    }

    @Override
    public void setPC(int pc) {
        this.PC = pc;
    }

    @Override
    public int getA() {
        return A;
    }

    @Override
    public void setA(int a) {
        this.A = a;
    }

    @Override
    public IMemory getMemory() {
        return memory;
    }

    @Override
    public int fetch() {
        int value = memory.read(PC);
        PC++;
        return value;
    }
}
