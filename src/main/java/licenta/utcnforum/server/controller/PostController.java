package licenta.utcnforum.server.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAll()
    {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Post> getById(@RequestParam UUID id)
    {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/utcn-news")
    public ResponseEntity<List<Post>> getAllByUtcn()
    {
        return ResponseEntity.ok(postService.getAllByUtcnUser());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Post>> getAllPostsByCategory(@RequestParam String categories)
    {
        Gson gson = new Gson();
        List<Category> categoryList = gson.fromJson(categories, new TypeToken<ArrayList<Category>>(){}.getType());
        return ResponseEntity.ok(postService.getAllByCategories(categoryList));
    }

    @PostMapping("/upsert")
    public ResponseEntity<Post> upsert(@RequestBody Post post)
    {
        return ResponseEntity.ok(postService.upsert(post));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Post post)
    {
        postService.delete(post);
        return ResponseEntity.ok().build();
    }

}
