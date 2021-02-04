package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagement.domain.Medicament;

import java.util.Collection;

@Repository
public interface MedicamentDao extends JpaRepository<Medicament,Long> {
    @Query("SELECT m from Medicament m where lower(m.name) like %:keyword% or lower(m.category.name) like %:categoryKeyword%")
    Collection<Medicament> getMedicamentsByNameKeywordAnAndCategoryNameKeyword(String keyword, String categoryKeyword);
}
