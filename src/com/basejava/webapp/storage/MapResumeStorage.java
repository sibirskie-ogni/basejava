package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getSearchKey( String uuid ) {
        return storage.get( uuid );
    }

    @Override
    protected void doSave( Object searchKey, Resume resume ) {
        storage.put( resume.getUuid(), resume );
    }

    @Override
    protected void doDelete( Object searchKey ) {
        storage.remove( ( (Resume) searchKey ).getUuid() );
    }

    @Override
    protected Resume doGet( Object searchKey ) {
        return (Resume) searchKey;
    }

    @Override
    protected void doUpdate( Object searchKey, Resume resume ) {
        storage.put( resume.getUuid(), resume );
    }

    @Override
    protected boolean isExist( Object searchKey ) {
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
