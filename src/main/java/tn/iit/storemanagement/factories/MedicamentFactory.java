package tn.iit.storemanagement.factories;

import tn.iit.storemanagement.domain.Medicament;
import tn.iit.storemanagement.dto.CategoryDto;
import tn.iit.storemanagement.dto.MedicamentDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MedicamentFactory {

    public static MedicamentDto medicamentToMedicamentDto(Medicament medicament)
    {
        if(medicament != null) {
            MedicamentDto medicamentDto = new MedicamentDto();
            medicamentDto.setId(medicament.getId());
            medicamentDto.setDosage(medicament.getDosage());
            medicamentDto.setExpiredDate(medicament.getExpiredDate());
            medicamentDto.setPrice(medicament.getPrice());
            medicamentDto.setName(medicament.getName());
            medicamentDto.setCategory(medicament.getCategory().getName());
            return medicamentDto;
        }else {
            return null;
        }
    }

    public static Medicament medicamentDtoToMedicament(MedicamentDto medicamentDto)
    {
        Medicament medicament = new Medicament ();
        medicament.setId (medicamentDto.getId ());
        medicament.setDosage (medicamentDto.getDosage ());
        medicament.setExpiredDate (medicamentDto.getExpiredDate ());
        medicament.setPrice (medicamentDto.getPrice ());
        medicament.setName (medicamentDto.getName ());
        return medicament;
    }

    public static Collection<MedicamentDto> medicamentsToMedicamentDtos(Collection<Medicament> medicaments)
    {
        List<MedicamentDto> medicamentDtoList = new ArrayList<> ();
        medicaments.forEach(medicament -> {
            medicamentDtoList.add (medicamentToMedicamentDto (medicament));
        });
        return medicamentDtoList;
    }
}
