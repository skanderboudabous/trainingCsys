package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagement.domain.Medicament;
@Repository
public interface MedicamentDao extends JpaRepository<Medicament,Long> {
}
