package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing( Resume::getFullName ).thenComparing( Resume::getUuid );

    @Override
    public final void update( Resume resume ) {
        LOG.info("Update " + resume);
        SK searchKey = getExistedSearchKey( resume.getUuid() );
        doUpdate( searchKey, resume );
    }

    @Override
    public final void delete( String uuid ) {
        LOG.info("Delete  " + uuid);
        SK searchKey = getExistedSearchKey( uuid );
        doDelete( searchKey );
    }

    @Override
    public final Resume get( String uuid ) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey( uuid );
        return doGet( searchKey );
    }

    @Override
    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedSearchKey( resume.getUuid() );
        doSave( searchKey, resume );
    }

    private SK getExistedSearchKey( String uuid ) {
        SK searchKey = getSearchKey( uuid );
        if ( !isExist( searchKey ) ) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException( uuid );
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey( String uuid ) {
        SK searchKey = getSearchKey( uuid );
        if ( isExist( searchKey ) ) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException( uuid );
        }
        return searchKey;
    }

    @Override
    public final List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumes = doGetAll();
        resumes.sort( RESUME_COMPARATOR );
        return resumes;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract SK getSearchKey( String uuid );

    protected abstract void doSave( SK searchKey, Resume resume );

    protected abstract void doDelete( SK searchKey );

    protected abstract Resume doGet( SK searchKey );

    protected abstract void doUpdate( SK searchKey, Resume resume );

    protected abstract boolean isExist( SK searchKey );
}
