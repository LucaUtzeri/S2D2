package lucauzeri.S2D2.services;


import lucauzeri.S2D2.entities.BlogPost;
import lucauzeri.S2D2.exceptions.BadRequestException;
import lucauzeri.S2D2.exceptions.NotFoundException;
import lucauzeri.S2D2.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAO blogPostDAO;

    public Page<BlogPost> getPosts(int pageNumber, int size, String orderBy){
        if(size > 100) size =100;
        Pageable pageable = (Pageable) PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostDAO.findAll(pageable);
    }

    public BlogPost savePost(BlogPost newPost) {
        BlogPostDAO.findByTitle(newPost.getTitle()).ifPresent(blogPost -> {
            throw new BadRequestException("Il titolo " + blogPost.getTitle() + " è già stato utilizzato");
        });
        newPost.setCover("https://ui-avatars.com/api/?title" + newPost.getTitle());
        return BlogPostDAO.save(newPost);
    }

    public BlogPost findById(UUID id) {
        return BlogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

//    public BlogPost findByIdAndUpdate(int id, BlogPost updatedPost) {
//        BlogPost found = null;
//        for (BlogPost post : this.postList) {
//            if (post.getId() == id) {
//                found = post;
//                found.setTitle(updatedPost.getTitle());
//                found.setContent(updatedPost.getContent());
//                found.setCover(updatedPost.getCover());
//            }
//        }
//        return found;
//    }

//    public void findByIdAndDelete(int id) {
//        Iterator<BlogPost> iterator = this.postList.iterator();
//        while (iterator.hasNext()) {
//            BlogPost current = iterator.next();
//            if (current.getId() == id) {
//                iterator.remove();
//            }
//        }
//
//    }
}
