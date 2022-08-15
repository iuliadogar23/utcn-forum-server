package licenta.utcnforum.server.service;

import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.model.Comment;
import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.persistence.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements ServiceInterface<Post>{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Override
    public Post upsert(Post saveObject) {
        Long date = Calendar.getInstance().getTimeInMillis();
        if (saveObject.getId() == null)
        {
            saveObject.setId(UUID.randomUUID());
            saveObject.setDate(date);
            if (saveObject.getUser()!=null && saveObject.getUser().getId()!=null) {
                User user = userService.findById(saveObject.getUser().getId());
                if (user.getUserPosts()==null)
                    user.setUserPosts(new ArrayList<>());
                user.getUserPosts().add(saveObject);
                userService.upsert(user);
            }
        }
        if (saveObject.getComments()!=null && !saveObject.getComments().isEmpty())
            for (Comment comment: saveObject.getComments()) {
                if (comment.getId()==null) {
                    comment.setId(UUID.randomUUID());
                    comment.setDate(date);
                }
            }
        return postRepository.save(saveObject);
    }

    @Override
    public List<Post> getAll() {
        return getPostsDescending(postRepository.findAll());
    }

    public List<Post> getAllByUtcnUser()
    {
        return getPostsDescending(userService.getAllPostsByAdminUser());
    }

    public List<Post> getAllByCategories(List<Category> categories)
    {
        return getPostsDescending(postRepository.getAllByCategories(categories.stream().map(c->c.getId()).collect(Collectors.toList())));
    }

    @Override
    public Post findById(UUID id) {
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
