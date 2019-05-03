package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.GeneralMst;
import io.github.jhipster.application.repository.GeneralMstRepository;
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
 * Integration tests for the {@Link GeneralMstResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class GeneralMstResourceIT {

    private static final Long DEFAULT_ORGID = 1L;
    private static final Long UPDATED_ORGID = 2L;

    private static final String DEFAULT_GENID = "AAAAAAAAAA";
    private static final String UPDATED_GENID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

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
    private GeneralMstRepository generalMstRepository;

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

    private MockMvc restGeneralMstMockMvc;

    private GeneralMst generalMst;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeneralMstResource generalMstResource = new GeneralMstResource(generalMstRepository);
        this.restGeneralMstMockMvc = MockMvcBuilders.standaloneSetup(generalMstResource)
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
    public static GeneralMst createEntity(EntityManager em) {
        GeneralMst generalMst = new GeneralMst()
            .orgid(DEFAULT_ORGID)
            .genid(DEFAULT_GENID)
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .defunct(DEFAULT_DEFUNCT)
            .creaton(DEFAULT_CREATON)
            .creatby(DEFAULT_CREATBY)
            .modifyon(DEFAULT_MODIFYON)
            .modifyby(DEFAULT_MODIFYBY);
        return generalMst;
    }

    @BeforeEach
    public void initTest() {
        generalMst = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeneralMst() throws Exception {
        int databaseSizeBeforeCreate = generalMstRepository.findAll().size();

        // Create the GeneralMst
        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isCreated());

        // Validate the GeneralMst in the database
        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeCreate + 1);
        GeneralMst testGeneralMst = generalMstList.get(generalMstList.size() - 1);
        assertThat(testGeneralMst.getOrgid()).isEqualTo(DEFAULT_ORGID);
        assertThat(testGeneralMst.getGenid()).isEqualTo(DEFAULT_GENID);
        assertThat(testGeneralMst.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGeneralMst.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGeneralMst.isDefunct()).isEqualTo(DEFAULT_DEFUNCT);
        assertThat(testGeneralMst.getCreaton()).isEqualTo(DEFAULT_CREATON);
        assertThat(testGeneralMst.getCreatby()).isEqualTo(DEFAULT_CREATBY);
        assertThat(testGeneralMst.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
        assertThat(testGeneralMst.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
    }

    @Test
    @Transactional
    public void createGeneralMstWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generalMstRepository.findAll().size();

        // Create the GeneralMst with an existing ID
        generalMst.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralMst in the database
        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgidIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setOrgid(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenidIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setGenid(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setType(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setName(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefunctIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setDefunct(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatonIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setCreaton(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatbyIsRequired() throws Exception {
        int databaseSizeBeforeTest = generalMstRepository.findAll().size();
        // set the field null
        generalMst.setCreatby(null);

        // Create the GeneralMst, which fails.

        restGeneralMstMockMvc.perform(post("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGeneralMsts() throws Exception {
        // Initialize the database
        generalMstRepository.saveAndFlush(generalMst);

        // Get all the generalMstList
        restGeneralMstMockMvc.perform(get("/api/general-msts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generalMst.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgid").value(hasItem(DEFAULT_ORGID.intValue())))
            .andExpect(jsonPath("$.[*].genid").value(hasItem(DEFAULT_GENID.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].defunct").value(hasItem(DEFAULT_DEFUNCT.booleanValue())))
            .andExpect(jsonPath("$.[*].creaton").value(hasItem(DEFAULT_CREATON.toString())))
            .andExpect(jsonPath("$.[*].creatby").value(hasItem(DEFAULT_CREATBY.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY.intValue())));
    }
    
    @Test
    @Transactional
    public void getGeneralMst() throws Exception {
        // Initialize the database
        generalMstRepository.saveAndFlush(generalMst);

        // Get the generalMst
        restGeneralMstMockMvc.perform(get("/api/general-msts/{id}", generalMst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(generalMst.getId().intValue()))
            .andExpect(jsonPath("$.orgid").value(DEFAULT_ORGID.intValue()))
            .andExpect(jsonPath("$.genid").value(DEFAULT_GENID.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.defunct").value(DEFAULT_DEFUNCT.booleanValue()))
            .andExpect(jsonPath("$.creaton").value(DEFAULT_CREATON.toString()))
            .andExpect(jsonPath("$.creatby").value(DEFAULT_CREATBY.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGeneralMst() throws Exception {
        // Get the generalMst
        restGeneralMstMockMvc.perform(get("/api/general-msts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeneralMst() throws Exception {
        // Initialize the database
        generalMstRepository.saveAndFlush(generalMst);

        int databaseSizeBeforeUpdate = generalMstRepository.findAll().size();

        // Update the generalMst
        GeneralMst updatedGeneralMst = generalMstRepository.findById(generalMst.getId()).get();
        // Disconnect from session so that the updates on updatedGeneralMst are not directly saved in db
        em.detach(updatedGeneralMst);
        updatedGeneralMst
            .orgid(UPDATED_ORGID)
            .genid(UPDATED_GENID)
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .defunct(UPDATED_DEFUNCT)
            .creaton(UPDATED_CREATON)
            .creatby(UPDATED_CREATBY)
            .modifyon(UPDATED_MODIFYON)
            .modifyby(UPDATED_MODIFYBY);

        restGeneralMstMockMvc.perform(put("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGeneralMst)))
            .andExpect(status().isOk());

        // Validate the GeneralMst in the database
        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeUpdate);
        GeneralMst testGeneralMst = generalMstList.get(generalMstList.size() - 1);
        assertThat(testGeneralMst.getOrgid()).isEqualTo(UPDATED_ORGID);
        assertThat(testGeneralMst.getGenid()).isEqualTo(UPDATED_GENID);
        assertThat(testGeneralMst.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGeneralMst.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGeneralMst.isDefunct()).isEqualTo(UPDATED_DEFUNCT);
        assertThat(testGeneralMst.getCreaton()).isEqualTo(UPDATED_CREATON);
        assertThat(testGeneralMst.getCreatby()).isEqualTo(UPDATED_CREATBY);
        assertThat(testGeneralMst.getModifyon()).isEqualTo(UPDATED_MODIFYON);
        assertThat(testGeneralMst.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
    }

    @Test
    @Transactional
    public void updateNonExistingGeneralMst() throws Exception {
        int databaseSizeBeforeUpdate = generalMstRepository.findAll().size();

        // Create the GeneralMst

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneralMstMockMvc.perform(put("/api/general-msts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(generalMst)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralMst in the database
        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeneralMst() throws Exception {
        // Initialize the database
        generalMstRepository.saveAndFlush(generalMst);

        int databaseSizeBeforeDelete = generalMstRepository.findAll().size();

        // Delete the generalMst
        restGeneralMstMockMvc.perform(delete("/api/general-msts/{id}", generalMst.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<GeneralMst> generalMstList = generalMstRepository.findAll();
        assertThat(generalMstList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeneralMst.class);
        GeneralMst generalMst1 = new GeneralMst();
        generalMst1.setId(1L);
        GeneralMst generalMst2 = new GeneralMst();
        generalMst2.setId(generalMst1.getId());
        assertThat(generalMst1).isEqualTo(generalMst2);
        generalMst2.setId(2L);
        assertThat(generalMst1).isNotEqualTo(generalMst2);
        generalMst1.setId(null);
        assertThat(generalMst1).isNotEqualTo(generalMst2);
    }
}
