package tn.iit.storemanagement.factories;

import tn.iit.storemanagement.domain.Category;
import tn.iit.storemanagement.domain.Medicament;
import tn.iit.storemanagement.dto.CategoryDto;
import tn.iit.storemanagement.dto.MedicamentDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryFactory {


    public static CategoryDto categoryToCategoryDto(Category category)
    {
        CategoryDto categoryDto = new CategoryDto ();
        categoryDto.setId (category.getId ());
        categoryDto.setName (category.getName ());
        categoryDto.setMedicaments(MedicamentFactory.medicamentsToMedicamentDtos(category.getMedicaments()));
        return categoryDto;
    }

    public static Category categoryDtoToCategory(CategoryDto categoryDto)
    {
        Category category = new Category ();
        category.setId (categoryDto.getId ());
        category.setName (categoryDto.getName ());
        Collection<Medicament> medicaments=new ArrayList<>();
        for(MedicamentDto medicamentDto:categoryDto.getMedicaments()){
            Medicament medicament=MedicamentFactory.medicamentDtoToMedicament(medicamentDto);
            medicament.setCategory(category);
            medicaments.add(medicament);
        }
        category.setMedicaments(medicaments);
        return category;
    }
    public static CategoryDto lazyCategoryToCategoryDto(Category category) {
        if (category != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId (category.getId ());
            categoryDto.setName (category.getName ());
            return categoryDto;
        } else {
            return null;
        }
    }
    public static Collection<CategoryDto> categoriesToCategoriesDtos(Collection<Category> categories)
    {
        List<CategoryDto> categoryDtoList = new ArrayList<> ();
        categories.forEach(category -> {
            categoryDtoList.add (categoryToCategoryDto (category));
        });
        return categoryDtoList;
    }
}
