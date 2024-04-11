/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 12 - Smaller Bigger Sort
 * Name: Michael Wood
 * Created: 4/11/2024
 */
package woodm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A bundle of test methods used to test the sorting methods in SmallerBiggerSort
 */
class SmallerBiggerSortTest {
    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(Arrays.asList(8, 3, 8, 5, 12, 1, 8, 18, 13));
    }

    @Test
    void smallerBiggerTest() {
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(null, 0, 1));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 1, 0));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, 0));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, list.size() + 1));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, -1, list.size()));
        assertThrows(IllegalArgumentException.class, () ->
                SmallerBiggerSort.smallerBigger(list, 0, -1));

        assertEquals(4, SmallerBiggerSort.smallerBigger(list, 2, 6));
        assertTrue(checkSmallerBigger(2, 6, 4));
        assertEquals(4, SmallerBiggerSort.smallerBigger(list, 0, list.size()));
        assertTrue(checkSmallerBigger(0, list.size(), 4));
        assertEquals(2, SmallerBiggerSort.smallerBigger(list, 1, list.size()));
        assertTrue(checkSmallerBigger(1, list.size(), 2));
        assertEquals(8, SmallerBiggerSort.smallerBigger(list, 7, list.size()));
        assertTrue(checkSmallerBigger(7, list.size(), 8));
    }

    private boolean checkSmallerBigger(int startIndex, int endIndex, int retIndex) {
        boolean inOrder = true;
        Integer value = list.get(retIndex);
        for(int i = startIndex; inOrder && i < retIndex; i++) {
            if(list.get(i).compareTo(value) > 0) {
                inOrder = false;
            }
        }
        for(int i = retIndex + 1; inOrder && i < endIndex; i++) {
            if(list.get(i).compareTo(value) < 0) {
                inOrder = false;
            }
        }
        return inOrder;
    }
}
