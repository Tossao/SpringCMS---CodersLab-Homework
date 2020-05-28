package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
