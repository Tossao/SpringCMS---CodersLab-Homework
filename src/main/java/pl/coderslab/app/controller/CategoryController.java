package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Category;
import pl.coderslab.app.repository.CategoryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
//    private final CategoryDao categoryDao;
//
//    public CategoryController(CategoryDao categoryDao) {
//        this.categoryDao = categoryDao;
//    }
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    @RequestMapping("add/{name}/{description}")
//    @ResponseBody
//    public String addCategory(@PathVariable String name, @PathVariable String description) {
//
//        Category category = new Category();
//        category.setName(name);
//        category.setDescription(description);
//        categoryDao.saveCategory(category);
//
//        return "Dodano kategorie: " + category.getName() + ", z ID= " + category.getId();
//    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public String deleteCategory(@PathVariable Long id) throws Exception {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.orElseThrow(Exception::new);
       // categoryDao.deleteCategory(category);
        categoryRepository.delete(category);
        String backLink = "<html><br><a href=\"/category/all\"> <<--- Back to Category List </a></html>";
        return "Skasowano kategorie: " + category.getName() + backLink;
    }

//    @RequestMapping("update/{id}/{name}/{description}")
//    @ResponseBody
//    public String updateCategory(@PathVariable Long id, @PathVariable String name, @PathVariable String description) {
//        Category category = categoryDao.findCategoryById(id);
//        category.setName(name);
//        category.setDescription(description);
//        categoryDao.updateCategory(category);
//        return "Kategoria: " + category.getName() + " - uaktualniona.";
//    }

    @GetMapping(value = "/all")
    public String getAllCategories(Model model) {
        //List<Category> categoryList = categoryDao.findAllCategories();
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("category", categoryList);
        return "category";
    }

    @RequestMapping(value = "addCategoryForm", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

//    @RequestMapping(value = "addCategoryForm", method = RequestMethod.POST)
//    public RedirectView create(@ModelAttribute Category category){
//        categoryDao.saveCategory(category);
//        return new RedirectView("/category/all");
//    }

    //Dodanie walidacji

    @RequestMapping(value = "addCategoryForm", method = RequestMethod.POST)
    public String create(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categoryForm";
        }
        //categoryDao.saveCategory(category);
        categoryRepository.save(category);
        return "redirect:/category/all";
    }

    @RequestMapping(value = "editCategoryForm/{id}", method = RequestMethod.GET)
    public String getEditCategoryForm(Model model, @PathVariable long id) throws Exception {
        Optional <Category> byId = categoryRepository.findById(id);
        Category category = byId.orElseThrow(Exception::new);
        //Category category = categoryDao.findCategoryById(id);
        model.addAttribute("category", category);
        return "categoryEditForm";
    }

//    @RequestMapping(value = "editCategoryForm/{id}", method = RequestMethod.POST)
//    public RedirectView update(@ModelAttribute Category category) {
//        categoryDao.updateCategory(category);
//        return new RedirectView("/category/all");
//    }

    @RequestMapping(value = "editCategoryForm/{id}", method = RequestMethod.POST)
    public String update(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categoryEditForm";
        }
        categoryRepository.save(category);
        //categoryDao.updateCategory(category);
        return "redirect:/category/all";
    }

}
