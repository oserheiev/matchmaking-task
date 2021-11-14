package com.playwing.matchmaking.utils;

public final class ArrayUtils {

    private ArrayUtils(){
    }

    public static <T> void swap(T[] array1, int index1, T[] array2, int index2) {
        T buffer = array1[index1];
        array1[index1] = array2[index2];
        array2[index2] = buffer;
    }
}
