package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Specility;
import io.github.jhipster.application.repository.SpecilityRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link SpecilityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SpecilityResourceIT {

    private static final Long DEFAULT_ORGID = 1L;
    private static final Long UPDATED_ORGID = 2L;

    private static final Long DEFAULT_SPLID = 1L;
    private static final Long UPDATED_SPLID = 2L;

    private static final String DEFAULT_SPLNAME = "AAAAAAAAAA";
    private static final String UPDATED_SPLNAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEFUNCT = false;
    private static final Boolean UPDATED_DEFUNCT = true;

    private static final Instant DEFAULT_CREATON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATBY = 1L;
    private static final Long UPDATED_CREATBY = 2L;

    private static final Instant DEFAULT_MODIFYON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFYON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MODIFYBY = 1L;
    private static final Long UPDATED_MODIFYBY = 2L;

    @Autowired
    private SpecilityRepository specilityRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSpecilityMockMvc;

    private Specility specility;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpecilityResource specilityResource = new SpecilityResource(specilityRepository);
        this.restSpecilityMockMvc = MockMvcBuilders.standaloneSetup(specilityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specility createEntity(EntityManager em) {
        Specility specility = new Specility()
            .orgid(DEFAULT_ORGID)
            .splid(DEFAULT_SPLID)
            .splname(DEFAULT_SPLNAME)
            .defunct(DEFAULT_DEFUNCT)
            .creaton(DEFAULT_CREATON)
            .creatby(DEFAULT_CREATBY)
            .modifyon(DEFAULT_MODIFYON)
            .modifyby(DEFAULT_MODIFYBY);
        return specility;
    }

    @BeforeEach
    public void initTest() {
        specility = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecility() throws Exception {
        int databaseSizeBeforeCreate = specilityRepository.findAll().size();

        // Create the Specility
        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isCreated());

        // Validate the Specility in the database
        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeCreate + 1);
        Specility testSpecility = specilityList.get(specilityList.size() - 1);
        assertThat(testSpecility.getOrgid()).isEqualTo(DEFAULT_ORGID);
        assertThat(testSpecility.getSplid()).isEqualTo(DEFAULT_SPLID);
        assertThat(testSpecility.getSplname()).isEqualTo(DEFAULT_SPLNAME);
        assertThat(testSpecility.isDefunct()).isEqualTo(DEFAULT_DEFUNCT);
        assertThat(testSpecility.getCreaton()).isEqualTo(DEFAULT_CREATON);
        assertThat(testSpecility.getCreatby()).isEqualTo(DEFAULT_CREATBY);
        assertThat(testSpecility.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
        assertThat(testSpecility.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
    }

    @Test
    @Transactional
    public void createSpecilityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specilityRepository.findAll().size();

        // Create the Specility with an existing ID
        specility.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        // Validate the Specility in the database
        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgidIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setOrgid(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSplidIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setSplid(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSplnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setSplname(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefunctIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setDefunct(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatonIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setCreaton(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatbyIsRequired() throws Exception {
        int databaseSizeBeforeTest = specilityRepository.findAll().size();
        // set the field null
        specility.setCreatby(null);

        // Create the Specility, which fails.

        restSpecilityMockMvc.perform(post("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecilities() throws Exception {
        // Initialize the database
        specilityRepository.saveAndFlush(specility);

        // Get all the specilityList
        restSpecilityMockMvc.perform(get("/api/specilities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specility.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgid").value(hasItem(DEFAULT_ORGID.intValue())))
            .andExpect(jsonPath("$.[*].splid").value(hasItem(DEFAULT_SPLID.intValue())))
            .andExpect(jsonPath("$.[*].splname").value(hasItem(DEFAULT_SPLNAME.toString())))
            .andExpect(jsonPath("$.[*].defunct").value(hasItem(DEFAULT_DEFUNCT.booleanValue())))
            .andExpect(jsonPath("$.[*].creaton").value(hasItem(DEFAULT_CREATON.toString())))
            .andExpect(jsonPath("$.[*].creatby").value(hasItem(DEFAULT_CREATBY.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY.intValue())));
    }
    
    @Test
    @Transactional
    public void getSpecility() throws Exception {
        // Initialize the database
        specilityRepository.saveAndFlush(specility);

        // Get the specility
        restSpecilityMockMvc.perform(get("/api/specilities/{id}", specility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(specility.getId().intValue()))
            .andExpect(jsonPath("$.orgid").value(DEFAULT_ORGID.intValue()))
            .andExpect(jsonPath("$.splid").value(DEFAULT_SPLID.intValue()))
            .andExpect(jsonPath("$.splname").value(DEFAULT_SPLNAME.toString()))
            .andExpect(jsonPath("$.defunct").value(DEFAULT_DEFUNCT.booleanValue()))
            .andExpect(jsonPath("$.creaton").value(DEFAULT_CREATON.toString()))
            .andExpect(jsonPath("$.creatby").value(DEFAULT_CREATBY.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSpecility() throws Exception {
        // Get the specility
        restSpecilityMockMvc.perform(get("/api/specilities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecility() throws Exception {
        // Initialize the database
        specilityRepository.saveAndFlush(specility);

        int databaseSizeBeforeUpdate = specilityRepository.findAll().size();

        // Update the specility
        Specility updatedSpecility = specilityRepository.findById(specility.getId()).get();
        // Disconnect from session so that the updates on updatedSpecility are not directly saved in db
        em.detach(updatedSpecility);
        updatedSpecility
            .orgid(UPDATED_ORGID)
            .splid(UPDATED_SPLID)
            .splname(UPDATED_SPLNAME)
            .defunct(UPDATED_DEFUNCT)
            .creaton(UPDATED_CREATON)
            .creatby(UPDATED_CREATBY)
            .modifyon(UPDATED_MODIFYON)
            .modifyby(UPDATED_MODIFYBY);

        restSpecilityMockMvc.perform(put("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpecility)))
            .andExpect(status().isOk());

        // Validate the Specility in the database
        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeUpdate);
        Specility testSpecility = specilityList.get(specilityList.size() - 1);
        assertThat(testSpecility.getOrgid()).isEqualTo(UPDATED_ORGID);
        assertThat(testSpecility.getSplid()).isEqualTo(UPDATED_SPLID);
        assertThat(testSpecility.getSplname()).isEqualTo(UPDATED_SPLNAME);
        assertThat(testSpecility.isDefunct()).isEqualTo(UPDATED_DEFUNCT);
        assertThat(testSpecility.getCreaton()).isEqualTo(UPDATED_CREATON);
        assertThat(testSpecility.getCreatby()).isEqualTo(UPDATED_CREATBY);
        assertThat(testSpecility.getModifyon()).isEqualTo(UPDATED_MODIFYON);
        assertThat(testSpecility.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecility() throws Exception {
        int databaseSizeBeforeUpdate = specilityRepository.findAll().size();

        // Create the Specility

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecilityMockMvc.perform(put("/api/specilities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specility)))
            .andExpect(status().isBadRequest());

        // Validate the Specility in the database
        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecility() throws Exception {
        // Initialize the database
        specilityRepository.saveAndFlush(specility);

        int databaseSizeBeforeDelete = specilityRepository.findAll().size();

        // Delete the specility
        restSpecilityMockMvc.perform(delete("/api/specilities/{id}", specility.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Specility> specilityList = specilityRepository.findAll();
        assertThat(specilityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specility.class);
        Specility specility1 = new Specility();
        specility1.setId(1L);
        Specility specility2 = new Specility();
        specility2.setId(specility1.getId());
        assertThat(specility1).isEqualTo(specility2);
        specility2.setId(2L);
        assertThat(specility1).isNotEqualTo(specility2);
        specility1.setId(null);
        assertThat(specility1).isNotEqualTo(specility2);
    }
}
