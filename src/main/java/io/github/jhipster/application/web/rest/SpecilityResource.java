package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Specility;
import io.github.jhipster.application.repository.SpecilityRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Specility}.
 */
@RestController
@RequestMapping("/api")
public class SpecilityResource {

    private final Logger log = LoggerFactory.getLogger(SpecilityResource.class);

    private static final String ENTITY_NAME = "specility";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecilityRepository specilityRepository;

    public SpecilityResource(SpecilityRepository specilityRepository) {
        this.specilityRepository = specilityRepository;
    }

    /**
     * {@code POST  /specilities} : Create a new specility.
     *
     * @param specility the specility to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specility, or with status {@code 400 (Bad Request)} if the specility has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specilities")
    public ResponseEntity<Specility> createSpecility(@Valid @RequestBody Specility specility) throws URISyntaxException {
        log.debug("REST request to save Specility : {}", specility);
        if (specility.getId() != null) {
            throw new BadRequestAlertException("A new specility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Specility result = specilityRepository.save(specility);
        return ResponseEntity.created(new URI("/api/specilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specilities} : Updates an existing specility.
     *
     * @param specility the specility to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specility,
     * or with status {@code 400 (Bad Request)} if the specility is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specility couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specilities")
    public ResponseEntity<Specility> updateSpecility(@Valid @RequestBody Specility specility) throws URISyntaxException {
        log.debug("REST request to update Specility : {}", specility);
        if (specility.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Specility result = specilityRepository.save(specility);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, specility.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specilities} : get all the specilities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specilities in body.
     */
    @GetMapping("/specilities")
    public List<Specility> getAllSpecilities() {
        log.debug("REST request to get all Specilities");
        return specilityRepository.findAll();
    }

    /**
     * {@code GET  /specilities/:id} : get the "id" specility.
     *
     * @param id the id of the specility to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specility, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specilities/{id}")
    public ResponseEntity<Specility> getSpecility(@PathVariable Long id) {
        log.debug("REST request to get Specility : {}", id);
        Optional<Specility> specility = specilityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(specility);
    }

    /**
     * {@code DELETE  /specilities/:id} : delete the "id" specility.
     *
     * @param id the id of the specility to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specilities/{id}")
    public ResponseEntity<Void> deleteSpecility(@PathVariable Long id) {
        log.debug("REST request to delete Specility : {}", id);
        specilityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
