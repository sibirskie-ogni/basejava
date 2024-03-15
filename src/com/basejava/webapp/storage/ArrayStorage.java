package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(int index) {
        if (index >= 0) {
            return true;
        }
        return false;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size <= storage.length) {
            if (isExist(findIndex(resume.getUuid())) == true) {
                System.out.println("ERROR: resume with this id " + resume.getUuid() +
                        " have been already created");
            } else {
                storage[size++] = resume;
            }
        } else System.out.println("ERROR: to many resumes in database");
    }

    void update(Resume resume) {
        if (isExist(findIndex(resume.getUuid())) == true) {
            storage[findIndex(resume.getUuid())] = resume;
        } else System.out.println("ERROR: resume with id " + resume.getUuid() + " do not exists");
    }

    public Resume get(String uuid) {
        if (isExist(findIndex(uuid)) == true) {
            return storage[findIndex(uuid)];
        }
        System.out.println("ERROR: resume with id " + uuid + " haven't been created");
        return null;
    }

    public void delete(String uuid) {
        if (isExist(findIndex(uuid)) == true) {
            storage[findIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("ERROR: resume with id " + uuid + " do not exists");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
