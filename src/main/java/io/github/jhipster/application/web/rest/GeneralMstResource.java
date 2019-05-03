package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.GeneralMst;
import io.github.jhipster.application.repository.GeneralMstRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.GeneralMst}.
 */
@RestController
@RequestMapping("/api")
public class GeneralMstResource {

    private final Logger log = LoggerFactory.getLogger(GeneralMstResource.class);

    private static final String ENTITY_NAME = "generalMst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeneralMstRepository generalMstRepository;

    public GeneralMstResource(GeneralMstRepository generalMstRepository) {
        this.generalMstRepository = generalMstRepository;
    }

    /**
     * {@code POST  /general-msts} : Create a new generalMst.
     *
     * @param generalMst the generalMst to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generalMst, or with status {@code 400 (Bad Request)} if the generalMst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/general-msts")
    public ResponseEntity<GeneralMst> createGeneralMst(@Valid @RequestBody GeneralMst generalMst) throws URISyntaxException {
        log.debug("REST request to save GeneralMst : {}", generalMst);
        if (generalMst.getId() != null) {
            throw new BadRequestAlertException("A new generalMst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralMst result = generalMstRepository.save(generalMst);
        return ResponseEntity.created(new URI("/api/general-msts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /general-msts} : Updates an existing generalMst.
     *
     * @param generalMst the generalMst to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generalMst,
     * or with status {@code 400 (Bad Request)} if the generalMst is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generalMst couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/general-msts")
    public ResponseEntity<GeneralMst> updateGeneralMst(@Valid @RequestBody GeneralMst generalMst) throws URISyntaxException {
        log.debug("REST request to update GeneralMst : {}", generalMst);
        if (generalMst.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneralMst result = generalMstRepository.save(generalMst);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, generalMst.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /general-msts} : get all the generalMsts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generalMsts in body.
     */
    @GetMapping("/general-msts")
    public List<GeneralMst> getAllGeneralMsts() {
        log.debug("REST request to get all GeneralMsts");
        return generalMstRepository.findAll();
    }

    /**
     * {@code GET  /general-msts/:id} : get the "id" generalMst.
     *
     * @param id the id of the generalMst to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generalMst, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/general-msts/{id}")
    public ResponseEntity<GeneralMst> getGeneralMst(@PathVariable Long id) {
        log.debug("REST request to get GeneralMst : {}", id);
        Optional<GeneralMst> generalMst = generalMstRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(generalMst);
    }

    /**
     * {@code DELETE  /general-msts/:id} : delete the "id" generalMst.
     *
     * @param id the id of the generalMst to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/general-msts/{id}")
    public ResponseEntity<Void> deleteGeneralMst(@PathVariable Long id) {
        log.debug("REST request to delete GeneralMst : {}", id);
        generalMstRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
