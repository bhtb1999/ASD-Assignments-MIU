package lab.edu.part1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ArrayFlattenerTest {

    private final ArrayFlattener arrayFlattener = new ArrayFlattener();

    @Test
    void shouldFlattenLegitNestedArray() {
        int[][] inputArray = {{1, 3}, {0}, {4, 5, 9}};

        int[] actual = arrayFlattener.flattenArray(inputArray);

        assertArrayEquals(new int[]{1, 3, 0, 4, 5, 9}, actual);
    }

    @Test
    void shouldReturnNullWhenInputArrayIsNull() {
        int[] actual = arrayFlattener.flattenArray(null);

        assertNull(actual);
    }
}
