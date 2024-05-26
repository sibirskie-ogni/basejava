package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey( String uuid ) {
        Resume searchKey = new Resume( uuid, uuid );
        return Arrays.binarySearch( storage, 0, size, searchKey );
    }

    @Override
    protected void doSave( Integer searchKey, Resume resume ) {
        if ( isExist( -(Integer) searchKey - 1 ) && storage[-(Integer) searchKey - 1] != null ) {
            if ( size - ( -(Integer) searchKey - 1 ) >= 0 ) {
                System.arraycopy( storage, -(Integer) searchKey - 1, storage, -(Integer) searchKey, size - ( -(Integer) searchKey - 1 ) );

            }
        }
        storage[-(Integer) searchKey - 1] = resume;
        size++;
    }

    @Override
    protected void doDelete( Integer searchKey ) {
        if ( isExist( searchKey ) ) {
            if ( size - (Integer) searchKey >= 0 ) {
                System.arraycopy( storage, (Integer) searchKey + 1, storage, (Integer) searchKey, size - (Integer) searchKey );
            }
            size--;
        }
    }

    @Override
    protected boolean isExist( Integer searchKey ) {
        return searchKey >= 0;
    }
}
