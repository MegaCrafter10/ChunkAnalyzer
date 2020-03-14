package megacrafter10.analyzer;

public class ChunkData implements Comparable<ChunkData> {
    public int x;
    public int z;
    public int entities;

    public ChunkData(int x, int z, int entities){
        this.x = x;
        this.z = z;
        this.entities = entities;
    }

    @Override
    public int compareTo(ChunkData other) {
        if (this.entities > other.entities){
            return 1;
        } else if (this.entities < other.entities){
            return -1;
        }
        return 0;
    }
}
