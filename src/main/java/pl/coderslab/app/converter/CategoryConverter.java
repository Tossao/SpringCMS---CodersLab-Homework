package pl.coderslab.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.dao.CategoryDao;
import pl.coderslab.app.entity.Category;

public class CategoryConverter implements Converter<String, Category> {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category convert(String sourceId) {
        return categoryDao.findCategoryById(Long.parseLong(sourceId));
    }
}
