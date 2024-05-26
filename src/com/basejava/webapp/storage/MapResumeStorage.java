package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey( String uuid ) {
        return storage.get( uuid );
    }

    @Override
    protected void doSave( Resume searchKey, Resume resume ) {
        storage.put( resume.getUuid(), resume );
    }

    @Override
    protected void doDelete( Resume searchKey ) {
        storage.remove( ( searchKey ).getUuid() );
    }

    @Override
    protected Resume doGet( Resume searchKey ) {
        return searchKey;
    }

    @Override
    protected void doUpdate( Resume searchKey, Resume resume ) {
        storage.put( resume.getUuid(), resume );
    }

    @Override
    protected boolean isExist( Resume searchKey ) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>( storage.values() );
    }

    @Override
    public int size() {
        return storage.size();
    }
}
