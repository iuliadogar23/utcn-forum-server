package licenta.utcnforum.server.service;

import org.bson.types.ObjectId;

import java.util.List;

public interface ServiceInterface<T> {

    T upsert(T saveObject);

    List<T> getAll();

    T findById(ObjectId id);

    void delete(T object);
}
