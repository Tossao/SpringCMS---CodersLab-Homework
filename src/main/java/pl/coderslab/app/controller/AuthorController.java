package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.repository.AuthorRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("author")
public class AuthorController {
//    private final AuthorDao authorDao;
//
//    public AuthorController(AuthorDao authorDao) {
//        this.authorDao = authorDao;
//    }
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


//    @RequestMapping("add/{firstName}/{lastName}")
//    @ResponseBody
//    public String addAuthor(@PathVariable String firstName, @PathVariable String lastName) {
//        Author author = new Author();
//        author.setFirstName(firstName);
//        author.setLastName(lastName);
//        authorDao.saveAuthor(author);
//        return "Dodano autora: " + author.getFirstName() + " " + author.getLastName()
//                + ", z ID =  " + author.getId();
//    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable Long id) throws Exception {
        Optional<Author> byId = authorRepository.findById(id);
        Author author = byId.orElseThrow(Exception::new);
        //authorDao.deleteAuthor(author);
        authorRepository.delete(author);
        String backLink = "<html><br><a href=\"/author/all\"> <<--- Back to Author List </a></html>";
        return "Skasowano autora: " + author.getFirstName() + " " + author.getLastName() + backLink;
    }


//    @RequestMapping("update/{id}/{firstName}/{lastName}")
//    @ResponseBody
//    public String updateAuthor(@PathVariable Long id, @PathVariable String firstName, @PathVariable String lastName) {
//        Author author = authorDao.findAuthorById(id);
//        author.setFirstName(firstName);
//        author.setLastName(lastName);
//        authorDao.updateAuthor(author);
//
//        return "Autor: " + author.getFirstName() + " " + author.getLastName() + " - uaktualniony.";
//    }

    @GetMapping(value = "/all")
    public String getAllAuthors(Model model) {
        List<Author> authorList = authorRepository.findAll();
        model.addAttribute("author", authorList);
        return "author";
    }

    @RequestMapping(value = "addAuthorForm", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("author", new Author());
        return "authorForm";
    }

//    @RequestMapping(value = "addAuthorForm", method = RequestMethod.POST)
//    public RedirectView create(@ModelAttribute Author author){
//        authorDao.saveAuthor(author);
//        return new RedirectView("/author/all");
//    }

    //Dodanie walidacji

    @RequestMapping(value = "addAuthorForm", method = RequestMethod.POST)
    public String create(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authorForm";
        }
        authorRepository.save(author);
        //authorDao.saveAuthor(author);
        return "redirect:/author/all";
    }

    @RequestMapping(value = "editAuthorForm/{id}", method = RequestMethod.GET)
    public String getEditAuthorForm(Model model, @PathVariable long id) throws Exception{
        Optional<Author> byId = authorRepository.findById(id);
        Author author = byId.orElseThrow(Exception::new);
        model.addAttribute("author", author);
        return "authorEditForm";
    }

//    @RequestMapping(value = "editAuthorForm/{id}", method = RequestMethod.POST)
//    public RedirectView update(@ModelAttribute Author author) {
//        authorDao.updateAuthor(author);
//        return new RedirectView("/author/all");
//    }

    @RequestMapping(value = "editAuthorForm/{id}", method = RequestMethod.POST)
    public String update(@Valid Author author, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "authorEditForm";
        }
      //  authorDao.updateAuthor(author);
        authorRepository.save(author);
        return "redirect:/author/all";
    }
}
