package it.monaco.medical.service.business.repositories;

import it.monaco.medical.service.model.entities.QuattroRuoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuattroRuoteRepository extends JpaRepository<QuattroRuoteItem, String>, QuattroRuoteRepositoryCustom{


}