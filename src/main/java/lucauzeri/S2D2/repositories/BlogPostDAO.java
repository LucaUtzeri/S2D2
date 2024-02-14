package lucauzeri.S2D2.repositories;

import lucauzeri.S2D2.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogPostDAO extends JpaRepository<BlogPost, UUID> {
    Optional<BlogPost> findByTitle(String title);

}
