package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size > storage.length) {
            System.out.println("ERROR: to many resumes in database");
        } else if (isExist(getIndex(resume.getUuid()))) {
            System.out.println("ERROR: resume with id " + resume.getUuid() +
                    " have been already created");
        } else {
            storage[size++] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: resume with id " + uuid + " do not exists");
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
