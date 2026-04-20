package pobj.tme6.memory;

public class MemorySlot {

    private int baseAddress;
    private IMemory memory;

    public MemorySlot(int baseAddress, IMemory memory) {
        this.baseAddress = baseAddress;
        this.memory = memory;
    }

    public int getBaseAddress() {
        return baseAddress;
    }

    public IMemory getMemory() {
        return memory;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MemorySlot))
            return false;
        MemorySlot other = (MemorySlot) obj;
        return this.baseAddress == other.baseAddress
                && this.memory == other.memory;
    }
}
