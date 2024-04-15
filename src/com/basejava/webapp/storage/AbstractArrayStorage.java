package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected static int size = 0;

    protected boolean isExist(int index) {
        return index >= 0;
    }

    public final int size() {
        return size;
    }

    public final void save(Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (isExist(getIndex(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume);
            size++;
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid() + " do not exists");
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            deleteResume(uuid);
            size--;
        } else {
            throw new NotExistStorageException(uuid + " do not exists");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid + " do not exists");
        }
        return storage[index];
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract int getIndex(String uuid);
}
