package com.basejava.webapp.storage;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest(ArrayStorage storage) {
        super(storage);
    }

    public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(ArrayStorageTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }
}