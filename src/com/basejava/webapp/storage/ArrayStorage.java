package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size <= storage.length) {
            if (size == 0) {
                storage[size++] = resume;
            } else
                for (int i = 0; i < size; i++) {
                    if (storage[i].uuid == resume.uuid) {
                        System.out.println("ERROR: resume with this id" + resume.uuid + " have been already created");
                        break;
                    } else if (i == size - 1) {
                        storage[size++] = resume;
                        break;
                    }
                }
        } else System.out.println("ERROR: to many resumes in database");
    }

    void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                storage[i] = resume;
                break;
            } else if (i == size - 1) System.out.println("ERROR: resume with id " + resume.uuid + " do not exists");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("ERROR: resume with id " + uuid + " haven't been created");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else if (i == size - 1) System.out.println("ERROR: resume with id " + uuid + " do not exists");
        }
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
