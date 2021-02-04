package tn.iit.storemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.storemanagement.domain.Composant;

public interface ComposantDao extends JpaRepository<Composant, Long> {
}
