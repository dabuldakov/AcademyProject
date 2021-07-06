package garbageFactory.Materials;

public class RecycleMaterialContainer<MATERIAL extends Material> {

    private int id;
    private double weightStart;
    private double weightEnd;

    private final Class<MATERIAL> type;

    public RecycleMaterialContainer(int id, double weightStart, Class<MATERIAL> type) {
        this.weightStart = weightStart;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeightStart() {
        return weightStart;
    }

    public void setWeightStart(double weightStart) {
        this.weightStart = weightStart;
    }

    public double getWeightEnd() {
        return weightEnd;
    }

    public void setWeightEnd(double weightEnd) {
        this.weightEnd = weightEnd;
    }

    public Class<MATERIAL> getType() {
        return type;
    }
}
