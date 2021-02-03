package tn.iit.storemanagement.web.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagement.dto.CategoryDto;
import tn.iit.storemanagement.services.CategoryService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@CrossOrigin("*")
@RequestMapping(value = "/api/categories")
@RestController()
public class CategoryResource {


    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public CategoryDto findOne(@PathVariable("id") long id) {
       return this.categoryService.findOne (id);
    }

    @GetMapping
    public Collection<CategoryDto> findAll(){
         return this.categoryService.findAll ();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(@Valid @RequestBody CategoryDto categoryDto,BindingResult bindingResults) throws URISyntaxException, MethodArgumentNotValidException {
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (categoryDto.getId() != null) {
            bindingResults.addError(new FieldError("Category", "code", "Post does not allow a category with id"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId())).body(result);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto,BindingResult bindingResults) throws MethodArgumentNotValidException {
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (categoryDto.getId() == null) {
            bindingResults.addError(new FieldError("Category", "code", "Put does not allow a category without id"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.categoryService.deleteById (id);
    }

}
