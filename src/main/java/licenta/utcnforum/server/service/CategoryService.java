package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.persistence.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ServiceInterface<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category upsert(Category saveObject) {
        if (saveObject.getUid()==null)
            saveObject.setUid(UUID.randomUUID());
        return categoryRepository.save(saveObject);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(ObjectId id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Category object) {
        categoryRepository.delete(object);
    }
}
