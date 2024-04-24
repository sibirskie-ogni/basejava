package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        if (isExist(-(Integer)searchKey - 1) && storage[-(Integer) searchKey - 1] != null) {
            for (int i = size; i > -(Integer) searchKey - 1; i--) {
                storage[i] = storage[i - 1];
            }
        }
        storage[-(Integer) searchKey - 1] = resume;
        size++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        if (isExist(searchKey)) {
            for (int i =(Integer) searchKey; i < size; i++) {
                storage[i] = storage[i + 1];
            }size--;
        }
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer)searchKey >=0;
    }
}
