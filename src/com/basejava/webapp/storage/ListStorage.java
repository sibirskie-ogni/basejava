package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.removeAll(storage);
    }

    @Override
    public void save(Resume resume) {
        saveAndException(resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void setUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected Resume setGet(String uuid) {
        int index = getIndex(uuid);
        return storage.get(index);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        storage.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }
}

