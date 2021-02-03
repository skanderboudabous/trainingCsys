package tn.iit.storemanagement.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.storemanagement.dao.CategoryDao;
import tn.iit.storemanagement.dao.MedicamentDao;
import tn.iit.storemanagement.domain.Category;
import tn.iit.storemanagement.domain.Medicament;
import tn.iit.storemanagement.dto.MedicamentDto;
import tn.iit.storemanagement.factories.MedicamentFactory;
import tn.iit.storemanagement.web.rest.errors.MyResourceNotFoundException;

import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class MedicamentService {

    private final MedicamentDao medicamentDao;
    private final CategoryDao categoryDao;

    public MedicamentService(MedicamentDao medicamentDao, CategoryDao categoryDao) {
        this.medicamentDao = medicamentDao;
        this.categoryDao = categoryDao;
    }

    public MedicamentDto save(MedicamentDto medicamentDto) {
        Category category=this.categoryDao.findCategoryByName (medicamentDto.getCategory())
                .orElseThrow ( () -> new MyResourceNotFoundException("Category with name " + medicamentDto.getCategory() + " not found"));
        Medicament medicament = MedicamentFactory.medicamentDtoToMedicament(medicamentDto);
        medicament.setCategory(category);
        this.medicamentDao.saveAndFlush (medicament);
        return medicamentDto;
    }
    @Transactional(readOnly = true)
    public MedicamentDto findOne(Long id){
        Medicament medicament=this.medicamentDao.findById (id)
                .orElseThrow ( () -> new MyResourceNotFoundException("Medicament with id " + id + " not found"));
        return MedicamentFactory.medicamentToMedicamentDto (medicament);
    }
    @Transactional( readOnly=true)
    public Collection<MedicamentDto> findAll(Pageable pageable){
        return MedicamentFactory.medicamentsToMedicamentDtos (this.medicamentDao.findAll (pageable).getContent ());
    }
    @Transactional(readOnly = true)
    public Collection<MedicamentDto> findAllByNameKeyworkandCategoryNameKeyword(String keyword,String nameKeyword){
        return MedicamentFactory.medicamentsToMedicamentDtos(this.medicamentDao.getMedicamentsByNameKeywordAnAndCategoryNameKeyword(keyword,nameKeyword));
    }
    @Transactional(readOnly = true)
    public Collection<MedicamentDto> findAllByIds(List<Long> ids){
        return MedicamentFactory.medicamentsToMedicamentDtos (this.medicamentDao.findAllById (ids));
    }
    public void deleteById(Long id){
        if(!this.medicamentDao.existsById (id))
            throw new MyResourceNotFoundException("Medicament is  missing with  id "+id);
        this.medicamentDao.deleteById (id);
    }
    public void deleteAllById(List<Long> ids) {
        for(Long id : ids){
            if(!this.medicamentDao.existsById (id))
                throw new MyResourceNotFoundException("Medicament is  missing with  id "+id);
            this.medicamentDao.deleteById (id);
        }
    }
}
