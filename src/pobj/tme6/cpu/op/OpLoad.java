package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpLoad implements IOpCode<ICPUState> {

    public OpLoad() {}

    @Override
    public int execute(ICPUState state) {
        int addr = state.fetch();
        int val = state.getMemory().read(addr);
        state.setA(val);
        return 2;
    }
}
