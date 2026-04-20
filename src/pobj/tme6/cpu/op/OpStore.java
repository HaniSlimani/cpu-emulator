package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpStore implements IOpCode<ICPUState> {

    public OpStore() {}

    @Override
    public int execute(ICPUState state) {
        int addr = state.fetch();
        state.getMemory().write(addr, state.getA());
        return 2;
    }
}
