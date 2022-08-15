package licenta.utcnforum.server.service;

import java.util.List;
import java.util.UUID;

public interface ServiceInterface<T> {

    T upsert(T saveObject);

    List<T> getAll();

    T findById(UUID id);

    void delete(T object);
}
