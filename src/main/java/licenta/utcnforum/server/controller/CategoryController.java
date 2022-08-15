package licenta.utcnforum.server.controller;

import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll()
    {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable ObjectId id)
    {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/upsert")
    public ResponseEntity<Category> upsert(@RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.upsert(category));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Category category)
    {
        categoryService.delete(category);
        return ResponseEntity.ok().build();
    }

}
