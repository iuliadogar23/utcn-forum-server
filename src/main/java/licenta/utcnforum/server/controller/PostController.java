package licenta.utcnforum.server.controller;

import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable ObjectId id)
    {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/utcn-news")
    public ResponseEntity<List<Post>> getAllByUtcn()
    {
        return ResponseEntity.ok(postService.getAllByUtcnUser());
    }

    @GetMapping("/category")
    public ResponseEntity<List<Post>> getAllPostsByCategory(@RequestParam String categoryUid)
    {
        return ResponseEntity.ok(postService.getAllByCategoryUid(categoryUid));
    }

    @GetMapping("/followed-categories")
    public ResponseEntity<List<Post>> getAllPostsForFollowedCategories(@RequestParam String userUuid)
    {
        return ResponseEntity.ok(postService.getAllByFollowedCategories(userUuid));
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
