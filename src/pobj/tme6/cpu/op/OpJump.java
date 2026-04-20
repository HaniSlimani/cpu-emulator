package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpJump implements IOpCode<ICPUState> {

    public OpJump() {}

    @Override
    public int execute(ICPUState state) {
        int arg = state.fetch();
        if (state.getA() > 0) {
            state.setPC(arg);
        }
        return 2;
    }
}
