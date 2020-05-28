package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Author> findAllAuthors() {
        Query query = entityManager.createQuery("SELECT author FROM Author author");
        List<Author> authors = query.getResultList();
        return authors;
    }

    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public void deleteAuthor(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public void updateAuthor(Author author) {
        entityManager.merge(author);
    }

    public Author findAuthorById(long id) {
        return entityManager.find(Author.class, id);
    }
}
