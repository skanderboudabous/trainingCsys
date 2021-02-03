package tn.iit.storemanagement.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tn.iit.storemanagement.dto.CategoryDto;
import tn.iit.storemanagement.services.CategoryService;
import tn.iit.storemanagement.utils.RestPreconditions;

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
    private final Logger logger= LoggerFactory.getLogger (CategoryResource.class);
    @GetMapping("{id}")
    public CategoryDto findOne(@PathVariable("id") long id) {
        this.logger.debug ("Getting Category {}",id);
        return this.categoryService.findOne (id);
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String pageSort) {
        this.logger.debug ("Getting all categories");
        return new ResponseEntity<>(categoryService.findAll(PageRequest.of (pageNo,pageSize, Sort.by (pageSort).ascending ())), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> add(@Valid @RequestBody CategoryDto categoryDto,BindingResult bindingResults) throws URISyntaxException, MethodArgumentNotValidException {
        this.logger.debug ("Adding new Category {}",categoryDto.getName());
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (categoryDto.getId() != null) {
            bindingResults.addError(new FieldError("Category", "id", "Post does not allow a category with id"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        RestPreconditions.checkBusinessLogic(!categoryService.nameExist(categoryDto.getName()),"Category " + categoryDto.getName()+" alredy exist");
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId())).body(result);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto,BindingResult bindingResults) throws MethodArgumentNotValidException {
        this.logger.debug ("Updating Category {} with {}",categoryDto.getId (),categoryDto.getName ());
        if (bindingResults.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        if (categoryDto.getId() == null) {
            bindingResults.addError(new FieldError("Category", "id", "Put does not allow a category without id"));
            throw new MethodArgumentNotValidException(null, bindingResults);
        }
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id){
        this.logger.debug ("Deleting Category {}",id);
        this.categoryService.deleteById (id);
    }

}
