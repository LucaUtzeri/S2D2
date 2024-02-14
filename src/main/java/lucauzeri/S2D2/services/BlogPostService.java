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

    public Page<BlogPost> getPosts(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = (Pageable) PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostDAO.findAll(pageable);
    }

    public BlogPost savePost(BlogPost newPost) {
        blogPostDAO.findById(newPost.getId()).ifPresent(blogPost -> {
            throw new BadRequestException("Il titolo " + blogPost.getTitle() + " è già stato utilizzato");
        });
        newPost.setCover("https://ui-avatars.com/api/?title" + newPost.getTitle());
        return blogPostDAO.save(newPost);
    }

    public BlogPost findById(UUID id) {
        return blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public BlogPost findByIdAndUpdate(UUID id, BlogPost updatedPost) {
        BlogPost found = this.findById(id);
        found.getTitle(updatedPost.getTitle());
        found.getCategory(updatedPost.getCategory());
        found.getContent(updatedPost.getContent());
        return blogPostDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        BlogPost found = this.findById(id);
        blogPostDAO.delete(found);
    }
}
