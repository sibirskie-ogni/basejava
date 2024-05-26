package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        if(size>=STORAGE_LIMIT){
            throw new StorageException("Storage exception", resume.getUuid());
        }
        storage[size++] = resume;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage[(int) searchKey] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}
