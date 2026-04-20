package lab.edu.part1;

import java.util.Arrays;

public class ArrayReversor {

    private final ArrayFlattenerService arrayFlattenerService;

    public ArrayReversor(ArrayFlattenerService arrayFlattenerService) {
        this.arrayFlattenerService = arrayFlattenerService;
    }

    public int[] reverseArray(int[][] inputArray) {
        int[] flattenedArray = arrayFlattenerService.flattenArray(inputArray);
        if (flattenedArray == null) {
            return null;
        }

        int[] reversedArray = new int[flattenedArray.length];
        for (int index = 0; index < flattenedArray.length; index++) {
            reversedArray[index] = flattenedArray[flattenedArray.length - 1 - index];
        }
        return reversedArray;
    }

    public static void main(String[] args) {
        ArrayReversor arrayReversor = new ArrayReversor(new ArrayFlattener()::flattenArray);
        int[][] sampleInput = {{1, 3}, {0}, {4, 5, 9}};
        System.out.println(Arrays.toString(arrayReversor.reverseArray(sampleInput)));
    }
}
