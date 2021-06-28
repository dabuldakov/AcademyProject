package katas;
//import com.ibm.jvm.format.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Codewards {

    private static long result1 = 0;
    private static int result = 0;
    private static int count = 0;

    public static void main(String[] args) {

        //System.out.println(getCount("asdfqwertyui"));
        //System.out.println(accum("RqaEzty"));
        //System.out.println(accum3("RqaEzty"));
        //String s = "1 7 3 -3 5";
        //System.out.println(highAndLow(s));
        //System.out.println(" " + squareDigits3(8118));
        //System.out.println(sortDesc(534214446));
        //System.out.println(getMiddle("t"));
        //System.out.println(isIsogram("DermatoglyphicsS"));
        //System.out.println(isSquare(26));
        //System.out.println(findShort("Let's travel abroad shall we"));
        //System.out.println(toJadenCase("Hello world i am here."));
        //System.out.println(makeComplement("ATTGC"));
        //System.out.println(solution(10));
        //System.out.println(digital_root(14));
        //System.out.println(spinWords("Hello wonderful world it is for fun"));
        //String[] s = {"Alex", "Jacob", "Mark", "Max"};
        //System.out.println(whoLikesIt(s));
//        System.out.println(duplicateCount("Indivisibilities"));
//        System.out.println(persistence(999));
        System.out.println(persistence1(4));
    }



    public static int persistence(long n) {
        int[] array = {0};
        recursion(n, array);
        return array[0];
    }

    private static void recursion(long n, int[] array) {
        char[] chars = String.valueOf(n).toCharArray();
        if (chars.length > 1){
            array[0]++;
            long result = 1;
            for (char aChar : chars) {
                result = result * Character.getNumericValue(aChar);
            }
            recursion(result, array);
        }
    }

    public static int persistence1(long n) {
            recursion1(n);
            return count;
    }

    private static void recursion1(long s) {
        String temp = String.valueOf(s);
        if (temp.length() > 1) {
            count++;
            result = temp.chars()
                    .mapToObj(x -> Character.getNumericValue((char) x))
                    .reduce((x, y) -> x * y)
                    .orElse(0);
            recursion1(result);
        }
    }

    public static int duplicateCount(String text) {
        char[] chars = text.toLowerCase().toCharArray();
        Arrays.sort(chars);
        int count = 0;
        boolean flag = false;
        for (int i = 1; i < chars.length ; i++) {
            if (chars[i] == chars[i-1]) {
                if (!flag) {
                    count++;
                    flag = true;
                }
            } else {
                flag = false;
            }
        }
        return count;
    }

    public static String whoLikesIt(String... names) {

            switch (names.length){
                case 0 : return "no one likes this";
                case 1 : return String.format("%s likes this", names[0]);
                case 2 : return String.format("%s and %s like this", names[0], names[1]);
                case 3 :  return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
                default : return String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
            }
    }

    public static String spinWords(String sentence) {

        return Arrays.stream(sentence.split(" "))
                .map(x -> (x.length() > 4) ? (new StringBuilder(x).reverse().toString()) : x)
                .collect(Collectors.joining(" "));
    }

    public static int digital_root(int n) {
        if (n > 9) {
            help(n);
            return result;
        } else {
            return n;
        }
    }

    private static void help(int s) {
        String temp = String.valueOf(s);
        if (temp.length() > 1) {
            result = temp.chars()
                    .mapToObj(x -> Character.getNumericValue((char) x))
                    .reduce((x, y) -> x + y)
                    .orElse(0);
            help(result);
        }
    }

    public static int solution(int number) {

        int result = 0;
        for (int i = 1; i < number; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                result = result + i;
            }
        }
        return result;
    }

    public int solution2(int number) {
        return IntStream.range(0, number)
                .filter(n -> (n % 3 == 0) || (n % 5 == 0))
                .sum();
    }

    public static String makeComplement(String dna) {

        char[] chars = dna.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'A':
                    chars[i] = 'T';
                    break;
                case 'T':
                    chars[i] = 'A';
                    break;
                case 'G':
                    chars[i] = 'C';
                    break;
                case 'C':
                    chars[i] = 'G';
                    break;
            }
        }

        return String.valueOf(chars);
    }

    public static String toJadenCase(String phrase) {

        if (phrase == null || phrase.equals(""))
            return null;

        String[] s = phrase.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String word : s) {
            char[] newArray = word.toCharArray();
            newArray[0] = Character.toUpperCase(word.charAt(0));
            list.add(String.valueOf(newArray));
        }

        return String.join(" ", list);
    }

    public static String toJadenCase2(String phrase) {
        if (null == phrase || phrase.length() == 0) {
            return null;
        }

        return Arrays.stream(phrase.split(" "))
                .map(i -> i.substring(0, 1).toUpperCase() + i.substring(1, i.length()))
                .collect(Collectors.joining(" "));
    }

    public static int findShort(String s) {

        return Stream.of(s.split(" "))
                .mapToInt(String::length)
                .min()
                .orElse(0);

    }

    public static boolean isSquare(int n) {
        double d = Math.sqrt(n);
        return (d - (int) d == 0);
    }

    public static boolean isIsogram(String str) {

        String stream = str
                .chars()
                .mapToObj(x -> Character.toLowerCase((char) x))
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return str.length() == stream.length();
    }

    public static boolean isIsogram2(String str) {

        return str
                .toLowerCase()
                .chars()
                .distinct()
                .count() == str.length();
    }

    public static String getMiddle(String word) {
        int mid = word.length() / 2;
        return (word.length() % 2 > 0)
                ? String.valueOf(word.charAt(mid))
                : word.charAt(mid - 1) + String.valueOf(word.charAt(mid));
    }

    public static int sortDesc(final int num) {

        String s = String.valueOf(num);

        String stream = s
                .chars()
                .mapToObj(x -> Character.getNumericValue((char) x))
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining());

        return Integer.valueOf(stream);
    }

    public static String squareDigits(int n) {

        String s = String.valueOf(n);
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char ch : c) {
            int i = Character.getNumericValue(ch);
            sb.append(i * i);
        }
        return sb.toString();
    }

    public static int squareDigits2(int n) {

        String s = String.valueOf(n);

        String stream = s
                .chars()
                .mapToObj(x -> Character.getNumericValue((char) x))
                .map(x -> x * x)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return Integer.valueOf(stream);
    }

    public static int squareDigits3(int n) {

        return Integer.valueOf(String.valueOf(n).chars()
                .mapToObj(x -> Character.getNumericValue((char) x))
                .map(x -> x * x)
                .map(String::valueOf)
                .collect(Collectors.joining()));

    }

    public static String highAndLow(String numbers) {

        String[] split = numbers.split(" ");
        int[] intStream = Arrays
                .stream(split)
                .mapToInt(Integer::valueOf)
                .toArray();

        return Arrays.stream(intStream).max().orElse(0) + " "
                + Arrays.stream(intStream).min().orElse(0);
    }

    public static String accum3(String s) {
        return IntStream
                .range(0, s.length())
                .mapToObj(i -> prepare3(s, i))
                .collect(Collectors.joining("-"));
    }

    public static String prepare3(String s, int index) {
        return IntStream
                .range(0, index + 1)
                .mapToObj(i -> (i == 0 ? Character.toUpperCase(s.charAt(index)) : Character.toLowerCase(s.charAt(index))))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static int getCount(String str) {

        char[] newArray = str.toCharArray();
        StringBuilder s = new StringBuilder();

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for (char c : newArray) {
            for (char c2 : vowels) {
                if (c == c2)
                    s.append(c);
            }
        }

        return s.length();
    }

    public static String accum(String s) {
        char[] newArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (char c : newArray) {
            sb.append(prepare(c, count));
            count++;
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    public static String prepare(char c, int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(c));
        count--;
        for (int i = 0; i < count; i++) {
            sb.append(Character.toLowerCase(c));
        }
        return sb.append('-').toString();
    }

    public static String accum2(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(i -> accumChar(s, i))
                .collect(Collectors.joining("-"));
    }

    public static String accumChar(String s, Integer index) {
        return IntStream.range(0, index + 1)
                .mapToObj(i -> i == 0 ? Character.toUpperCase(s.charAt(index)) : Character.toLowerCase(s.charAt(index)))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
