package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
