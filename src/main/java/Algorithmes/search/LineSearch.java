package Algorithmes.search;

class LineSearch extends Parent implements Searcher{

    public String getNAME() {
        return "Line";
    }

    @Override
    public Answer search(int[] array, int number) {
        int count = 0;
        Answer answer = new Answer(-1, count);
        answer.setStartTime(System.nanoTime());

        for (int i=0; i<array.length; i++) {
            count++;
            if (array[i] == number){
                answer.setLookingIndex(i);
                answer.setIteration(count);
                answer.setEndTime(System.nanoTime());
                return answer;
            }
        }
        answer.setEndTime(System.nanoTime());
        return answer;
    }
}
