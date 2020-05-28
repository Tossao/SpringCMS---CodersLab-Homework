package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Article;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.repository.ArticleRepository;
import pl.coderslab.app.repository.AuthorRepository;
import pl.coderslab.app.repository.CategoryRepository;
import pl.coderslab.app.validation.DraftValidationGroup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("draft")
public class DraftController {

//    private final AuthorDao authorDao;
//    private final CategoryDao categoryDao;
//    private final ArticleDao articleDao;
//
//    public DraftController(AuthorDao authorDao, CategoryDao categoryDao, ArticleDao articleDao) {
//        this.authorDao = authorDao;
//        this.categoryDao = categoryDao;
//        this.articleDao = articleDao;
//    }

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    public DraftController(AuthorRepository authorRepository, CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }


    @GetMapping(value = "/all")
    public String getAllDrafts(Model model) {
        //List<Article> articleDrafts = articleDao.findAllDraft();
        List<Article> articleDrafts = articleRepository.findAllByDraftIsTrue();
        model.addAttribute("articleDrafts", articleDrafts);
        return "draft";
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable Long id) throws Exception {
        Optional<Article> byId = articleRepository.findById(id);
        Article article = byId.orElseThrow(Exception::new);
       // articleDao.deleteArticle(article);
        articleRepository.delete(article);
        String backLink = "<html><br><a href=\"/draft/all\"> <<--- Back to Article List </a></html>";
        return "Skasowano propozycje artykulu: " + article.getTitle() + backLink;
    }

    @RequestMapping(value = "addDraftForm", method = RequestMethod.GET)
    public String getDraftForm(Model model) {
        model.addAttribute("draft", new Article());
        return "draftForm";
    }

    @RequestMapping(value = "addDraftForm", method = RequestMethod.POST)
    public String create(@ModelAttribute("draft") @Validated({DraftValidationGroup.class}) Article draft, BindingResult result) {
        if (result.hasErrors()) {
            return "draftForm";
        }
        draft.setCreated(LocalDateTime.now());
     //   articleDao.saveArticle(draft);
        articleRepository.save(draft);
        return "redirect:/draft/all";
    }

    @RequestMapping(value = "editDraftForm/{id}", method = RequestMethod.GET)
    public String getEditDraftForm(Model model, @PathVariable long id) throws Exception {
        Optional<Article> byId = articleRepository.findById(id);
        Article article = byId.orElseThrow(Exception::new);
        model.addAttribute("draft", article);
        return "draftEditForm";
    }

    @RequestMapping(value = "editDraftForm/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("draft") @Validated({DraftValidationGroup.class}) Article draft, BindingResult result,
                         @PathVariable Long id) throws Exception {
        if (result.hasErrors()) {
            return "draftForm";
        }
        Optional<Article> byId = articleRepository.findById(id);
        Article draftById = byId.orElseThrow(Exception::new);

        draftById.setTitle(draft.getTitle());
        draftById.setContent(draft.getContent());
        draftById.setCreated(draftById.getCreated());
        draftById.setUpdated(LocalDateTime.now());

//        draft.setCreated(articleDao.findArticleById(draft.getId()).getCreated());
//        draft.setUpdated(LocalDateTime.now());
        articleRepository.save(draftById);
        //articleDao.updateArticle(draft);
        return "redirect:/draft/all";
    }

    @ModelAttribute("category")
    public List<Category> categoryList() {
       // return categoryDao.findAllCategories();
        return categoryRepository.findAll();
    }

    @ModelAttribute("author")
    public List<Author> authorList() {
      //  return authorDao.findAllAuthors();
        return authorRepository.findAll();
    }

    @ModelAttribute("article")
    public List<Article> articleList() {
        return articleRepository.findAll();
      //  return articleDao.findAllArticles();
    }
}
