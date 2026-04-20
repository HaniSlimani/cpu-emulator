package pobj.tme6.cpu;

import java.util.ArrayList;
import java.util.List;

import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpSet;
import pobj.tme6.cpu.op.OpAdd;
import pobj.tme6.cpu.op.OpLoad;
import pobj.tme6.cpu.op.OpStore;
import pobj.tme6.cpu.op.OpJump;

import pobj.tme6.memory.IMemory;

public class CPU implements ICPU {

    private ICPUState state;
    private List<IOpCode<ICPUState>> ops;

    // Getter pour les tests
    public ICPUState getState() {
        return state;
    }

    // Constructeur : initialise state et table d'opcodes
    public CPU(IMemory memory) {
        this.state = new CPUState(memory);
        this.ops = new ArrayList<>();
        // Indices 0 à 4 : OpSet, OpAdd, OpLoad, OpStore, OpJump
        ops.add(new OpSet());   // opcode 0
        ops.add(new OpAdd());   // opcode 1
        ops.add(new OpLoad());  // opcode 2
        ops.add(new OpStore()); // opcode 3
        ops.add(new OpJump());  // opcode 4
    }

    @Override
    public void reset() {
        state.setA(0);
        state.setPC(0);
    }

    @Override
    public int execute() {
        int opcode = state.fetch(); // lit le code d'opération à PC et incrémente PC
        if (opcode >= 0 && opcode < ops.size()) {
            return ops.get(opcode).execute(state); // délégation à l'opcode
        } else {
            // NOP : instruction non valide, durée = 1
            return 1;
        }
    }
}
