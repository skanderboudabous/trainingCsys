package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagement.domain.Category;
@Repository
public interface CategoryDao  extends JpaRepository<Category,Long> {
}
