/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if(size<=storage.length){
        if(size==0){
            storage[size++]=resume;
        }else
        for (int i = 0; i < size; i++) {
            if(storage[i].uuid == resume.uuid){
                System.out.println("ERROR: resume with this id have been already created");
                break;
            }else if(i==size-1){
                storage[size++]=resume;
                break;
            }
        }
        }else System.out.println("ERROR: to many resumes in database");
    }

    void update(Resume resume){
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                storage[i]=resume;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }System.out.println("ERROR: resume with id "+uuid+" haven't been created");
        return null;
    }

    void delete(String uuid) {
        
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size-1]=null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
