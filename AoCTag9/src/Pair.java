import java.util.Objects;

public class Pair {
    boolean file;
    int index;
    int size;

    public Pair(boolean file, int index, int size){
        this.file = file;
        this.index = index;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return file == pair.file && index == pair.index && size == pair.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, index, size);
    }
}
