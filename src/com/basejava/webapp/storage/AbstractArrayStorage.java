package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected static int size = 0;

    public final int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public List<Resume> doGetAll(){
        return Arrays.asList(Arrays.copyOf(storage,size));
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[(int) searchKey];
    }
}
