package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ArrayStorage;
import com.basejava.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1","Ivan Ivanov");
        Resume r2 = new Resume("uuid2","Sergey Sergeev");
        Resume r3 = new Resume("uuid3","Aleksandr Aleksandrov");
        Resume r4 = new Resume("uuid4","Petr Petrov");
        Resume r5 = new Resume("uuid3","Victor Victorov");

        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);
        STORAGE.save(r4);
        STORAGE.update(r5);

        System.out.println("Get r1: " + STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + STORAGE.size());
//        System.out.println("Get dummy: " + STORAGE.get("dummy"));

        printAll();
        STORAGE.delete(r2.getUuid());
        printAll();
        STORAGE.save(r2);
        printAll();
        STORAGE.clear();
        printAll();
        System.out.println("Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
