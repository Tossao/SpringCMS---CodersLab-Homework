package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.ArticleDao;
import pl.coderslab.app.dao.AuthorDao;
import pl.coderslab.app.dao.CategoryDao;
import pl.coderslab.app.entity.Article;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.repository.ArticleRepository;
import pl.coderslab.app.repository.AuthorRepository;
import pl.coderslab.app.repository.CategoryRepository;
import pl.coderslab.app.validation.ArticleValidationGroup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("article")
public class ArticleController {

    //    private final ArticleDao articleDao;
//    private final AuthorDao authorDao;
//    private final CategoryDao categoryDao;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public ArticleController(ArticleDao articleDao, AuthorDao authorDao, CategoryDao categoryDao, ArticleRepository articleRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
//        this.articleDao = articleDao;
//        this.authorDao = authorDao;
//        this.categoryDao = categoryDao;
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

//    @RequestMapping("add/{title}/{authorId}/{categoryId}/{content}")
//    @ResponseBody
//    public String addArticle(@PathVariable String title, @PathVariable Long authorId,
//                             @PathVariable Long categoryId, @PathVariable String content) {
//        Article article = new Article();
//        article.setTitle(title);
//        article.setAuthor(authorDao.findAuthorById(authorId));
//        article.getCategory().add(categoryDao.findCategoryById(categoryId));
//        article.setContent(content);
//        article.setCreated(LocalDateTime.now());
//        articleDao.saveArticle(article);
//        return "Dodano artykul: " + article.getTitle() + ", z ID = " + article.getId();
//    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable Long id) throws Exception {
        Optional<Article> byId = articleRepository.findById(id);
        Article article = byId.orElseThrow(Exception::new);
        // articleDao.deleteArticle(article);
        articleRepository.delete(article);
        String backLink = "<html><br><a href=\"/article/all\"> <<--- Back to Article List </a></html>";
        return "Skasowano artykul!" + article.getTitle() +  backLink;
    }

//    @RequestMapping("update/{id}/{title}/{content}")
//    @ResponseBody
//    public String updateArticle(@PathVariable Long id, @PathVariable String title,
//                                @PathVariable String content) {
//        Article article = articleDao.findArticleById(id);
//        article.setTitle(title);
//        article.setContent(content);
//        article.setUpdated(LocalDateTime.now());
//        articleDao.updateArticle(article);
//        return "Artykul: " + article.getTitle() + " - uaktualniony: " + "\n" + article.toString();
//    }

    @GetMapping(value = "/all")
    public String getAllArticles(Model model) {
        // List<Article> articleList = articleDao.findAllArticles();
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("article", articleList);
        return "article";
    }

    @RequestMapping(value = "addArticleForm", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("article", new Article());
        return "articleForm";
    }

    @RequestMapping(value = "addArticleForm", method = RequestMethod.POST)
    public String create(@Validated(ArticleValidationGroup.class) Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "articleForm";
        }
        article.setCreated(LocalDateTime.now());
        // articleDao.saveArticle(article);
        articleRepository.save(article);
        return "redirect:/article/all";
    }

    @RequestMapping(value = "editArticleForm/{id}", method = RequestMethod.GET)
    public String getEditArticleForm(Model model, @PathVariable long id) throws Exception {
        Optional<Article> byId = articleRepository.findById(id);
        Article article = byId.orElseThrow(Exception::new);
        model.addAttribute("article", article);
        return "articleEditForm";
    }

    @RequestMapping(value = "editArticleForm/{id}", method = RequestMethod.POST)
    public String update(@Validated(ArticleValidationGroup.class) Article article, BindingResult result, @PathVariable Long id) throws Exception {
        if (result.hasErrors()) {
            return "articleEditForm";
        }
        Optional<Article> byId = articleRepository.findById(id);
        Article articleById = byId.orElseThrow(Exception::new);

//        articleById.setCreated(articleDao.findArticleById(article.getId()).getCreated());
        articleById.setTitle(article.getTitle());
        articleById.setContent(article.getContent());
        articleById.setCreated(articleById.getCreated());
        articleById.setUpdated(LocalDateTime.now());
        //articleDao.updateArticle(article);
        articleRepository.save(articleById);
        return "redirect:/article/all";
    }

    @GetMapping("/categoryTable/{id}")
    public String findAllByCategory(@PathVariable Long id, Model model) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = categoryOptional.orElseThrow(Exception::new);
        List<Article> articles = articleRepository.findByCategory(category);
        model.addAttribute("articlesCat", articles);
        return "articleCategory";
    }

    @ModelAttribute("category")
    public List<Category> categoryList() {
        //return categoryDao.findAllCategories();
        return categoryRepository.findAll();
    }

    @ModelAttribute("author")
    public List<Author> authorList() {
        //return authorDao.findAllAuthors();
        return authorRepository.findAll();
    }
}
