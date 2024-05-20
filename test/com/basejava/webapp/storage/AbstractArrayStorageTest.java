package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest( Storage storage ) {
        super( storage );
    }

    @Test( expected = StorageException.class )
    public void saveOverflow() {
        for ( int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++ ) {
            storage.save( new Resume( "uuid " + i, EXAMPLE_NAME + i ) );
        }
        assertSize( storage.size() );
        storage.save( new Resume( "uuid10001", EXAMPLE_NAME ) );
    }
}
