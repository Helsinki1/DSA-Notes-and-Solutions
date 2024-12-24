/**
 * @author Brian S. Borowski
 * Test cases for Programming Assignment 7 - MyHashMap
 * COMS W3134
 * Last modified: 11/17/2022
 */
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashMapTestCases {

    @Test
    public void test01() {
        MyMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.size());
        assertEquals(true, map.isEmpty());
        Integer returnVal = map.put("ten", 10);
        assertEquals(null, returnVal);
        assertEquals(1, map.size());
        assertEquals(false, map.isEmpty());
        returnVal = map.put("ten", 10);
        assertEquals(10, returnVal);
        assertEquals(1, map.size());
        assertEquals(false, map.isEmpty());
    }

    @Test
    public void test02() {
        MyMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.size());
        assertEquals(true, map.isEmpty());
        Integer returnVal = map.get("ten");
        assertEquals(null, returnVal);
        returnVal = map.put("ten", 10);
        assertEquals(null, returnVal);
        returnVal = map.get("ten");
        assertEquals(10, returnVal);
        assertEquals(1, map.size());
        assertEquals(false, map.isEmpty());
    }

    @Test
    public void test03() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 1; i <= 1000; i++) {
            map.put(String.valueOf(i), i);
        }
        for (int i = 1000; i >= 1; i--) {
            assertEquals(i, map.get(String.valueOf(i)));
        }
        assertEquals(1000, map.size());
        assertEquals(1733, map.getTableSize());
    }

    @Test
    public void test04() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 1; i <= 10000; i++) {
            map.put(String.valueOf(i), i - 1);
        }
        assertEquals(10000, map.size());
        assertEquals(13901, map.getTableSize());
        for (int i = 1; i <= 10000; i++) {
            Integer returnVal = map.put(String.valueOf(i), i);
            assertEquals(i - 1, returnVal);
        }
        for (int i = 10000; i >= 1; i--) {
            assertEquals(i, map.get(String.valueOf(i)));
        }
        assertEquals(10000, map.size());
        assertEquals(13901, map.getTableSize());
        assertEquals(0.7193727069994964, map.getLoadFactor(), 1e-7);
        assertEquals(4, map.computeMaxChainLength());
    }

    @Test
    public void test05() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 1; i <= 300000; i++) {
            map.put(String.valueOf(i), i - 1);
        }
        assertEquals(300000, map.size());
        assertEquals(222461, map.getTableSize());
        assertEquals(1.3485509819698733, map.getLoadFactor(), 1e-7);
        assertEquals(6, map.computeMaxChainLength());
        for (int i = 1; i <= 300000; i++) {
            Integer returnVal = map.put(String.valueOf(i), i);
            assertEquals(i - 1, returnVal);
        }
        assertEquals(300000, map.size());
        assertEquals(222461, map.getTableSize());
        assertEquals(1.3485509819698733, map.getLoadFactor(), 1e-7);
        assertEquals(6, map.computeMaxChainLength());
        for (int i = 300000; i >= 1; i--) {
            assertEquals(i, map.get(String.valueOf(i)));
            Integer returnVal = map.remove(String.valueOf(i));
            assertEquals(i, returnVal);
            returnVal = map.remove(String.valueOf(i));
            assertEquals(null, returnVal);
            assertEquals(i - 1, map.size());
            assertEquals(222461, map.getTableSize());
        }
        assertEquals(0, map.size());
        assertEquals(222461, map.getTableSize());
        assertEquals(0.0, map.getLoadFactor(), 1e-7);
        assertEquals(0, map.computeMaxChainLength());
    }
}
