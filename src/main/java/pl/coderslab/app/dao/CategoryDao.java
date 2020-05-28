package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CategoryDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> findAllCategories() {
        Query query = entityManager.createQuery("SELECT category FROM Category category");
        List<Category> categories = query.getResultList();
        return categories;
    }

    public void saveCategory(Category category) {
        entityManager.persist(category);
    }

    public void deleteCategory(Category category) {

        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
    }

    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    public Category findCategoryById(long id) {
        return entityManager.find(Category.class, id);
    }
}

