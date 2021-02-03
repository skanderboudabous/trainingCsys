package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.iit.storemanagement.domain.Category;

import java.util.Optional;

@Repository
public interface CategoryDao  extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryByName(String name);
    Boolean existsByName(String name);
}
