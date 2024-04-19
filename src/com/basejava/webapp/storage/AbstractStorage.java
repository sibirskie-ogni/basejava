package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (isExist(index)) {
            setUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid() + " do not exists");
        }
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (isExist(index)) {
            deleteResume(uuid);
        } else {
            throw new NotExistStorageException(uuid + " do not exists");
        }
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid + " do not exists");
        }
        return setGet(uuid);
    }

    public final void saveAndException(Resume resume) {
        if (isExist(getIndex(resume.getUuid()))) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume);
        }
    }

    protected abstract void saveResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume setGet(String uuid);

    protected abstract void setUpdate(int index, Resume resume);

    protected abstract int getIndex(String uuid);

    protected boolean isExist(int index) {
        return index >= 0;
    }
}
