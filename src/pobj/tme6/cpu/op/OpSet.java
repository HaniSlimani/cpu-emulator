package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpSet implements IOpCode<ICPUState> {

    public OpSet() {}

    @Override
    public int execute(ICPUState state) {
        int arg = state.fetch();
        state.setA(arg);
        return 2;
    }
}
