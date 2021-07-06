package garbageFactory.Materials;

import garbageFactory.Constants;

public class RecycleMaterialContainer<MATERIAL extends Material> {

    private double weightStart;
    private double weightEnd;
    private final Class<MATERIAL> type;

    public RecycleMaterialContainer(double weight, Class<MATERIAL> type) {
        this.type = type;
        this.weightStart = weight;
        this.weightEnd = 0;
    }

    public Class<MATERIAL> getType() {
        return type;
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

    @Override
    public String toString() {
        return "RecycleMaterialContainer{" +
                "weightStart=" + weightStart +
                ", weightEnd=" + weightEnd +
                ", type=" + Constants.ANSI_PURPLE + type.getSimpleName() + Constants.ANSI_RESET +
                '}';
    }
}
