package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.persistence.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostService implements ServiceInterface<Post>{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post upsert(Post saveObject) {
        return postRepository.save(saveObject);
    }

    @Override
    public List<Post> getAll() {
        return getPostsDescending(postRepository.findAll());
    }

    public List<Post> getAllByUtcnUser()
    {
        return getPostsDescending(postRepository.getAllByUtcnUser("adminuser@utcn.com"));
    }

    public List<Post> getAllByCategories(List<Category> categories)
    {
        return getPostsDescending(postRepository.getAllByCategories(categories));
    }

    @Override
    public Post findById(ObjectId id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Post object) {
        postRepository.delete(object);
    }

    private List<Post> getPostsDescending(List<Post> posts)
    {
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }

}
