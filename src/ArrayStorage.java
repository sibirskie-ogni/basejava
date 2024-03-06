/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];


    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[0] == null) {
                storage[0] = r;
                break;
            } else if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }


    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
                break;
            } else return null;
        }
        return resume;
    }

    void delete(String uuid) {
        int counter = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                counter++;
            } else counter++;
        }
        Resume[] resumes = new Resume[10000];
        for (int i = 0, j = 0; i < resumes.length; i++) {
            if (storage[i] != null) {
                resumes[j] = storage[i];
                j++;
            }
        }
        storage = resumes;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {

        int counter = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else counter++;
        }
        Resume[] resumes = new Resume[counter];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }


    int size() {
        int counter = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else counter++;
        }
        return counter;
    }
}
