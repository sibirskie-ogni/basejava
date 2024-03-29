package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = -getIndex(resume.getUuid());
        if (size > storage.length) {
            System.out.println("ERROR: to many resumes in database");
        } else if (isExist(index - 1) && storage[index - 1] != null) {
            for (int i = size; i > index - 1; i--) {
                storage[i] = storage[i - 1];
            }
            storage[index - 1] = resume;
            size++;
        } else {
            storage[index - 1] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        } else {
            System.out.println("ERROR: resume with id " + uuid + " do not exists");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
