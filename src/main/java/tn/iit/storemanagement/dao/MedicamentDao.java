package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagement.domain.Medicament;

import java.util.Collection;

@Repository
public interface MedicamentDao extends JpaRepository<Medicament,Long> {
    @Query(value = "select * from medicament m, category c where m.name like %:keyword% or (c.name like %:categoryKeyword% and c.id=m.category_id) ",nativeQuery = true)
    Collection<Medicament> getMedicamentsByNameKeywordAnAndCategoryNameKeyword(String keyword, String categoryKeyword);
}
