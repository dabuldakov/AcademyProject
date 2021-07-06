package garbageFactory.Materials;

public class RecycleMaterialContainer {

    private int id;
    private Type type;
    private double weightStart;
    private double weightEnd;


    public RecycleMaterialContainer(int id, Type type, double weightStart) {
        this.id = id;
        this.type = type;
        this.weightStart = weightStart;
        this.weightEnd = 0F;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

}
