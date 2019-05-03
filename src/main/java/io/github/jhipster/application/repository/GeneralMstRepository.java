package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.GeneralMst;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GeneralMst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralMstRepository extends JpaRepository<GeneralMst, Long> {

}
