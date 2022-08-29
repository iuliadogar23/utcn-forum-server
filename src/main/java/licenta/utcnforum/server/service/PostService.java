package licenta.utcnforum.server.service;

import licenta.utcnforum.server.email.EmailDetails;
import licenta.utcnforum.server.email.EmailService;
import licenta.utcnforum.server.model.Category;
import licenta.utcnforum.server.model.Comment;
import licenta.utcnforum.server.model.Post;
import licenta.utcnforum.server.model.User;
import licenta.utcnforum.server.persistence.PostRepository;
import org.bson.types.ObjectId;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EmailService emailService;

    @Override
    public Post upsert(Post saveObject) {
        Long date = Calendar.getInstance().getTimeInMillis();
        if (saveObject.getUuid() == null)
        {
            saveObject.setUuid(UUID.randomUUID());
            saveObject.setDate(date);
            if (saveObject.getUser()!=null && saveObject.getUser().getId()!=null) {
                User user = userService.findById(saveObject.getUser().getId());
                if (user.getUserPosts()==null)
                    user.setUserPosts(new ArrayList<>());
                if(saveObject.getUser().isUtcn()!=user.isUtcn())
                    user.setUtcn(saveObject.getUser().isUtcn());
                user.getUserPosts().add(saveObject);
                userService.upsert(user);
            }
        }
        List<Comment> comments = new ArrayList<>();
        if (saveObject.getComments()!=null && !saveObject.getComments().isEmpty())
            for (Comment comment: saveObject.getComments()) {
                if (comment.getId()==null) {
                    comment.setId(UUID.randomUUID());
                    comment.setDate(date);
                    emailService.sendSimpleMail(setupEmail(saveObject, comment));
                }
                comments.add(comment);
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

    public List<Post> getAllByCategoryUid(String categoryUid)
    {
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryByUid(UUID.fromString(categoryUid)));
        return getAllByCategories(categories);
    }

    public List<Post> getAllByFollowedCategories(String userUuid)
    {
        return getAllByCategories(userService.getFollowCategoriesForUser(userUuid));
    }

    private List<Post> getAllByCategories(List<Category> categories)
    {

        return getPostsDescending(postRepository.getAllByCategories(categories.stream().map(c->c.getUuid()).collect(Collectors.toList())));
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
        posts.sort(Comparator.comparing(Post::getDate).reversed());
        return posts;
    }

    private EmailDetails setupEmail(Post post, Comment comment)
    {
        StringBuilder message = new StringBuilder();
        User user = userService.getByUid(post.getUser().getUid());

        if (user!=null){
            message.append(comment.getUser().getDisplayName())
                    .append(" a adaugat un comentariu nou la urmatorul post:")
                    .append("\n")
                    .append(post.getText())
                    .append("\n")
                    .append("\n")
                    .append("Vezi comentariul pe site: ")
                    .append("http://localhost:4200/post");
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setMsgBody(message.toString());
            emailDetails.setSubject("Comentariu nou de la " + comment.getUser().getDisplayName());
            emailDetails.setRecipient(user.getEmail());
        }
        return null;
    }

}
