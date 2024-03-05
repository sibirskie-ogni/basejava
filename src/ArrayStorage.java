/**
 * Array based storage for Resumes
 */
import java.lang.*;
import java.util.Arrays;
import java.util.List;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];


    void clear() {
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[0]==null){
                storage[0] = r;
                break;
            }else if(storage[i]==null){
                storage[i] =r;
                break;
            }
        }
    }


    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return storage.length;
    }
}
