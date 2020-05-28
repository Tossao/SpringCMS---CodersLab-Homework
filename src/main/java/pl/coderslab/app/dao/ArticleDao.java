package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ArticleDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Article> findAllArticles() {
        Query query = entityManager.createQuery("SELECT article FROM Article article LEFT JOIN FETCH article.category category WHERE article.draft = false");
        List<Article> articles = query.getResultList();
        return articles;
    }

    public List<Article> findLastFiveArticles() {
        Query query = entityManager.createQuery("SELECT article FROM Article article WHERE article.draft=false ORDER BY article.created DESC ");
        List<Article> articles = query.setMaxResults(5).getResultList();
        return articles;
    }

    public List<Article> findAllDraft() {
        Query query = entityManager.createQuery("SELECT article FROM Article article WHERE article.draft=true ");
        List<Article> articles = query.getResultList();
        return articles;
    }

    public void saveArticle(Article article) {
        entityManager.persist(article);
    }

    public void deleteArticle(Article article) {
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
    }

    public void updateArticle(Article article) {
        entityManager.merge(article);
    }

    public Article findArticleById(long id) {
        return entityManager.find(Article.class, id);
    }


}
