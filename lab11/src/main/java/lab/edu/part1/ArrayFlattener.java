package lab.edu.part1;

import java.util.Arrays;

public class ArrayFlattener {

    public int[] flattenArray(int[][] inputArray) {
        if (inputArray == null) {
            return null;
        }

        int totalLength = 0;
        for (int[] nestedArray : inputArray) {
            if (nestedArray != null) {
                totalLength += nestedArray.length;
            }
        }

        int[] flattenedArray = new int[totalLength];
        int currentIndex = 0;
        for (int[] nestedArray : inputArray) {
            if (nestedArray == null) {
                continue;
            }
            for (int value : nestedArray) {
                flattenedArray[currentIndex++] = value;
            }
        }
        return flattenedArray;
    }

    public static void main(String[] args) {
        ArrayFlattener arrayFlattener = new ArrayFlattener();
        int[][] sampleInput = {{1, 3}, {0}, {4, 5, 9}};
        System.out.println(Arrays.toString(arrayFlattener.flattenArray(sampleInput)));
    }
}
