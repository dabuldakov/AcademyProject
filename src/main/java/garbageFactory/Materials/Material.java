package garbageFactory.Materials;

public class Material{
    private int id;
    private double weightStart;
    private double weightEnd;

    public Material(int id, double weightStart) {
        this.id = id;
        this.weightStart = weightStart;
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
}

