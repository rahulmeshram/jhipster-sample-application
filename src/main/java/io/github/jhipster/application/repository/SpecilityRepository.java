package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Specility;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Specility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecilityRepository extends JpaRepository<Specility, Long> {

}
