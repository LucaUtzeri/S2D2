package lucauzeri.S2D2.controllers;

import lucauzeri.S2D2.entities.BlogPost;
import lucauzeri.S2D2.services.BlogPostService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // 1. GET
    @GetMapping
    public Page<BlogPost> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return this.blogPostService.getPosts(page, size, orderBy);
    }

    // 2. POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // CREATED = Status Code 201
    public BlogPost savePost(@RequestBody BlogPost newPost) {
        return this.blogPostService.savePost(newPost);
    }

    // 3. GET (+ {id})
    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable UUID id) {
        return this.blogPostService.findById(id);
    }


    // 4. PUT (+ {id}) (+ body)
    @PutMapping("/{id}")
    public BlogPost findByIdAndUpdate(@PathVariable UUID id, @RequestBody BlogPost updatedPost) {
        return this.blogPostService.findByIdAndUpdate(id, updatedPost);
    }

    // 5. DELETE ( +{id})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //NO_CONTENT = Status Code 204
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.blogPostService.findByIdAndDelete(id);
    }
}