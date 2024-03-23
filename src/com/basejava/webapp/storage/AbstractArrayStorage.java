package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected boolean isExist(int index) {
        return index >= 0;
    }

    public int size() {
        return size;
    }

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

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: resume with id " + resume.getUuid() + " do not exists");
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

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
