package algorithmes.search;

public interface Searcher {
    Answer search(int[] array, int number);
    public long getSum();
    public void setSum(long sum);
    public int getCounter();
    public void setCounter(int counter);
    public int[][] getArrays();
    public void setArrays(int[][] arrays);
    public String getNAME();
}
