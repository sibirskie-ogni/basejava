package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid3");

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
        for (Resume r : STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
