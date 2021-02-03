package tn.iit.storemanagement.services;

import org.aspectj.apache.bcel.classfile.annotation.TypeAnnotationGen;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.iit.storemanagement.dao.CategoryDao;
import tn.iit.storemanagement.domain.Category;
import tn.iit.storemanagement.domain.Medicament;
import tn.iit.storemanagement.dto.CategoryDto;
import tn.iit.storemanagement.factories.CategoryFactory;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.storemanagement.web.rest.errors.MyResourceNotFoundException;

import java.util.Collection;

@Transactional
@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryDto save(CategoryDto categoryDto) {
         this.categoryDao.saveAndFlush (CategoryFactory.categoryDtoToCategory (categoryDto));
         return categoryDto;
    }
    public void deleteById(Long id){
        if(!this.categoryDao.existsById (id))
            throw new MyResourceNotFoundException("Category is  missing with  id "+id);
        this.categoryDao.deleteById (id);
    }
    @Transactional(readOnly = true)
    public CategoryDto findOne(Long id){
        Category category=this.categoryDao.findById (id)
                .orElseThrow ( () -> new MyResourceNotFoundException("Category with id " + id + " not found"));
        return CategoryFactory.categoryToCategoryDto (category);
    }
    @Transactional(readOnly = true)
    public Collection<CategoryDto> findAll(Pageable pageable){
        return CategoryFactory.categoriesToCategoriesDtos (this.categoryDao.findAll (pageable).getContent());
    }
    public boolean nameExist(String name){
        return this.categoryDao.existsByName(name);
    }

}
