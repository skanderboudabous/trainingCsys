package tn.iit.storemanagement.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.iit.storemanagement.dao.MedicamentDao;
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

    public MedicamentService(MedicamentDao medicamentDao) {
        this.medicamentDao = medicamentDao;
    }

    public MedicamentDto save(MedicamentDto medicamentDto) {
        this.medicamentDao.saveAndFlush (MedicamentFactory.medicamentDtoToMedicament (medicamentDto));
        return medicamentDto;
    }

    public void deleteById(Long id){
        if(!this.medicamentDao.existsById (id))
            throw new MyResourceNotFoundException("Medicament is  missing with  id "+id);
        this.medicamentDao.deleteById (id);
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
    public Collection<MedicamentDto> findAllByIds(List<Long> ids){
        return MedicamentFactory.medicamentsToMedicamentDtos (this.medicamentDao.findAllById (ids));
    }
}
