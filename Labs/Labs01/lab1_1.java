// Lab 1.1

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

final class IntegerArray {
    private int [] arr;

    IntegerArray() {
        arr = new int[0];
    }

    IntegerArray(int [] arr) {
        this.arr = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public int length() {
        return arr.length;
    }

    public int getElementAt(int i) {
        return i >= 0 && i < arr.length ? arr[i] : -1;
    }

    public int sum() {
        int s = 0;
        for(int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        return s;
    }

    public double average() {
        return (double)sum() / arr.length;
    }

    public IntegerArray getSorted() {
        int [] sorted = arr.clone();
        Arrays.sort(sorted);
        return new IntegerArray(sorted);
    }

    public IntegerArray concat(IntegerArray ia) {
        int len = arr.length;
        int [] newArr = new int[len + ia.length()];

        for(int i = 0; i < len; i++) {
            newArr[i] = arr[i];
        }

        for(int i = 0; i < ia.length(); i++) {
            newArr[i + len] = ia.getElementAt(i);
        }

        return new IntegerArray(newArr);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");

        for(int i = 0; i < arr.length; i++) {
            str.append(arr[i]);
            if(i < arr.length - 1) {
                str.append(", ");
            }
        }

        str.append("]");

        return str.toString();
    }

    public boolean equals(IntegerArray other) {
        if(arr.length != other.length()) {
            return false;
        }

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != other.getElementAt(i)) {
                return false;
            }
        }

        return true;
    }
}

class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) {
        Scanner in = new Scanner(input);

        int n = in.nextInt();
        int [] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        in.close();
        return new IntegerArray(arr);
    }
}

public class IntegerArrayTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        IntegerArray ia = null;
        switch (s) {
            case "testSimpleMethods":
                ia = new IntegerArray(generateRandomArray(scanner.nextInt()));
                testSimpleMethods(ia);
                break;
            case "testConcat":
                testConcat(scanner);
                break;
            case "testEquals":
                testEquals(scanner);
                break;
            case "testSorting":
                testSorting(scanner);
                break;
            case "testReading":
                testReading(new ByteArrayInputStream(scanner.nextLine().getBytes()));
                break;
            case "testImmutability":
                int a[] = generateRandomArray(scanner.nextInt());
                ia = new IntegerArray(a);
                testSimpleMethods(ia);
                testSimpleMethods(ia);
                IntegerArray sorted_ia = ia.getSorted();
                testSimpleMethods(ia);
                testSimpleMethods(sorted_ia);
                sorted_ia.getSorted();
                testSimpleMethods(sorted_ia);
                testSimpleMethods(ia);
                a[0] += 2;
                testSimpleMethods(ia);
                ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(integerArrayToString(ia).getBytes()));
                testSimpleMethods(ia);
                break;
        }
        scanner.close();
    }

    static void testReading(InputStream in) {
        IntegerArray read = ArrayReader.readIntegerArray(in);
        System.out.println(read);
    }

    static void testSorting(Scanner scanner) {
        int[] a = readArray(scanner);
        IntegerArray ia = new IntegerArray(a);
        System.out.println(ia.getSorted());
    }

    static void testEquals(Scanner scanner) {
        int[] a = readArray(scanner);
        int[] b = readArray(scanner);
        int[] c = readArray(scanner);
        IntegerArray ia = new IntegerArray(a);
        IntegerArray ib = new IntegerArray(b);
        IntegerArray ic = new IntegerArray(c);
        System.out.println(ia.equals(ib));
        System.out.println(ia.equals(ic));
        System.out.println(ib.equals(ic));
    }

    static void testConcat(Scanner scanner) {
        int[] a = readArray(scanner);
        int[] b = readArray(scanner);
        IntegerArray array1 = new IntegerArray(a);
        IntegerArray array2 = new IntegerArray(b);
        IntegerArray concatenated = array1.concat(array2);
        System.out.println(concatenated);
    }

    static void testSimpleMethods(IntegerArray ia) {
        System.out.print(integerArrayToString(ia));
        System.out.println(ia);
        System.out.println(ia.sum());
        System.out.printf("%.2f\n", ia.average());
    }


    static String integerArrayToString(IntegerArray ia) {
        StringBuilder sb = new StringBuilder();
        sb.append(ia.length()).append('\n');
        for (int i = 0; i < ia.length(); ++i)
            sb.append(ia.getElementAt(i)).append(' ');
        sb.append('\n');
        return sb.toString();
    }

    static int[] readArray(Scanner scanner) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }
        return a;
    }


    static int[] generateRandomArray(int k) {
        Random rnd = new Random(k);
        int n = rnd.nextInt(8) + 2;
        int a[] = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = rnd.nextInt(20) - 5;
        }
        return a;
    }

}
