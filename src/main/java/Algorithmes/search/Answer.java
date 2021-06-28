package Algorithmes.search;

class Answer {
    private int lookingIndex;
    private int iteration;
    private long startTime;
    private long endTime;

    Answer(int lookingIndex, int iteration) {
        this.lookingIndex = lookingIndex;
        this.iteration = iteration;
    }

    int getLookingIndex() {
        return lookingIndex;
    }

    void setLookingIndex(int lookingIndex) {
        this.lookingIndex = lookingIndex;
    }

    int getIteration() {
        return iteration;
    }

    void setIteration(int iteration) {
        this.iteration = iteration;
    }

    long getStartTime() {
        return startTime;
    }

    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    long getEndTime() {
        return endTime;
    }

    void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
