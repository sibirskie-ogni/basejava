package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected static int size = 0;

    protected final boolean isExist(int index) {
        return index >= 0;
    }

    public final int size() {
        return size;
    }

    public abstract void save(Resume resume);

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: resume with id " + resume.getUuid() + " do not exists");
        }
    }

    public abstract void delete(String uuid);

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
    public final void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
