package lab.edu.part1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArrayReversorTest {

    @Mock
    private ArrayFlattenerService arrayFlattenerService;

    @Test
    void shouldReverseFlattenedArrayForLegitNestedArray() {
        int[][] inputArray = {{1, 3}, {0}, {4, 5, 9}};
        when(arrayFlattenerService.flattenArray(inputArray)).thenReturn(new int[]{1, 3, 0, 4, 5, 9});

        ArrayReversor arrayReversor = new ArrayReversor(arrayFlattenerService);

        int[] actual = arrayReversor.reverseArray(inputArray);

        assertArrayEquals(new int[]{9, 5, 4, 0, 3, 1}, actual);
        verify(arrayFlattenerService, times(1)).flattenArray(inputArray);
    }

    @Test
    void shouldReturnNullWhenInputArrayIsNull() {
        when(arrayFlattenerService.flattenArray(null)).thenReturn(null);

        ArrayReversor arrayReversor = new ArrayReversor(arrayFlattenerService);

        int[] actual = arrayReversor.reverseArray(null);

        assertNull(actual);
        verify(arrayFlattenerService, times(1)).flattenArray(null);
    }
}
