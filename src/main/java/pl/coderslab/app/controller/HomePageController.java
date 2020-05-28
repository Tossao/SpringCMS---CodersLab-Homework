package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.entity.Article;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.repository.ArticleRepository;
import pl.coderslab.app.repository.CategoryRepository;

import java.util.List;

@Controller
public class HomePageController {
  //  private final ArticleDao articleDao;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;


    public HomePageController(
            //ArticleDao articleDao,
            CategoryRepository categoryRepository, ArticleRepository articleRepository) {
       // this.articleDao = articleDao;
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @RequestMapping("/")
    public String home(Model model) {
       List<Category> categoryList = categoryRepository.findAll();
       List<Article> articleList = articleRepository.findFirst5ByOrderByCreatedDesc();
        // List<Article> articleList = articleDao.findLastFiveArticles();

        model.addAttribute("article", articleList);
        model.addAttribute("category", categoryList);

        return "home";
    }

}

