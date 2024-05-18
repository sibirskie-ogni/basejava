package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid3";
    private static final String DUMMY = "dummy";

    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume4 = new Resume(UUID_4);
    private static final Resume resume5 = new Resume(UUID_5);

    private static final Resume[] noResumes = {};
    private static final int INITIAL_SIZE = 3;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() throws Exception {
        assertSize(storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(storage.size());
        Assert.assertArrayEquals(noResumes, storage.getAll());
    }

    @Test
    public void update() throws Exception {
        storage.update(resume5);
        Assert.assertSame(resume5, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertArrayEquals(new Resume[]{new Resume(UUID_1),
                        new Resume(UUID_2),
                        new Resume(UUID_3)
                },
                storage.getAll());
    }

    @Test
    public void save() throws Exception {
        assertSize(INITIAL_SIZE);
        storage.save(new Resume(UUID_4));
        assertSize(INITIAL_SIZE + 1);
        assertGet(storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resume1);
        assertGet(resume1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(INITIAL_SIZE - 1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
            for (int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid " + i));
            }
            assertSize(storage.size());
            storage.save(new Resume("uuid10001"));
    }

    public void assertSize(int size) {
        Assert.assertEquals(storage.size(), size);
    }

    public void assertGet(Resume resume) {
        Assert.assertSame(storage.get(resume.getUuid()), resume);
    }
}