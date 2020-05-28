package pl.coderslab.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.dao.AuthorDao;
import pl.coderslab.app.entity.Author;

public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    AuthorDao authorDao;

    @Override
    public Author convert(String sourceId) {
        return authorDao.findAuthorById(Long.parseLong(sourceId));
    }

}
