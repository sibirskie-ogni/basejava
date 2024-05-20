package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing( Resume::getFullName ).thenComparing( Resume::getUuid );

    @Override
    public final void update( Resume resume ) {
        Object searchKey = getExistedSearchKey( resume.getUuid() );
        doUpdate( searchKey, resume );
    }

    @Override
    public final void delete( String uuid ) {
        Object searchKey = getExistedSearchKey( uuid );
        doDelete( searchKey );
    }

    @Override
    public final Resume get( String uuid ) {
        Object searchKey = getExistedSearchKey( uuid );
        return doGet( searchKey );
    }

    @Override
    public final void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey( resume.getUuid() );
        doSave( searchKey, resume );
    }

    private Object getExistedSearchKey( String uuid ) {
        Object searchKey = getSearchKey( uuid );
        if ( !isExist( searchKey ) ) {
            throw new NotExistStorageException( uuid );
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey( String uuid ) {
        Object searchKey = getSearchKey( uuid );
        if ( isExist( searchKey ) ) {
            throw new ExistStorageException( uuid );
        }
        return searchKey;
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> resumes = doGetAll();
        resumes.sort( RESUME_COMPARATOR );
        return resumes;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract Object getSearchKey( String uuid );

    protected abstract void doSave( Object searchKey, Resume resume );

    protected abstract void doDelete( Object searchKey );

    protected abstract Resume doGet( Object searchKey );

    protected abstract void doUpdate( Object searchKey, Resume resume );

    protected abstract boolean isExist( Object searchKey );
}
