package pobj.tme6.memory;

import java.util.ArrayList;
import java.util.List;

public class AddressDecoder implements ICopyableMemory {

    private int size;
    private List<MemorySlot> slots;

    public AddressDecoder(int size) {
        this.size = size;
        this.slots = new ArrayList<>();
    }

    public void add(MemorySlot slot) {
        slots.add(slot);
    }

    public void remove(MemorySlot slot) {
        slots.remove(slot);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int read(int addr) {
        for (MemorySlot slot : slots) {
            int base = slot.getBaseAddress();
            IMemory mem = slot.getMemory();
            if (addr >= base && addr < base + mem.size()) {
                return mem.read(addr - base);
            }
        }
        throw new IllegalArgumentException("Invalid address: " + addr);
    }

    @Override
    public void write(int addr, int val) {
        for (MemorySlot slot : slots) {
            int base = slot.getBaseAddress();
            IMemory mem = slot.getMemory();
            if (addr >= base && addr < base + mem.size()) {
                mem.write(addr - base, val);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid address: " + addr);
    }

    @Override
    public ICopyableMemory copy() {
        AddressDecoder dec = new AddressDecoder(this.size);

        for (MemorySlot slot : slots) {
            IMemory mem = slot.getMemory();

            if (!(mem instanceof ICopyableMemory)) {
                throw new UnsupportedOperationException(
                        "Memory not copyable: " + mem.getClass());
            }

            ICopyableMemory memCopy =
                    ((ICopyableMemory) mem).copy();

            dec.add(new MemorySlot(
                    slot.getBaseAddress(),
                    memCopy));
        }

        return dec;
    }
}
