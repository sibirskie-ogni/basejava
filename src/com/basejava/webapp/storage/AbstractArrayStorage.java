package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected static int size = 0;

    public final int size() {
        return size;
    }

    public final void save(Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else saveAndException(resume);
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    @Override
    protected void setUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume setGet(String uuid) {
        int index = getIndex(uuid);
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
