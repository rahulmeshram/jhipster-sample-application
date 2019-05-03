package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Doctor;
import io.github.jhipster.application.repository.DoctorRepository;
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
 * Integration tests for the {@Link DoctorResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DoctorResourceIT {

    private static final Long DEFAULT_ORGID = 1L;
    private static final Long UPDATED_ORGID = 2L;

    private static final Long DEFAULT_DOCID = 1L;
    private static final Long UPDATED_DOCID = 2L;

    private static final String DEFAULT_DOCNAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SPLID = 1L;
    private static final Long UPDATED_SPLID = 2L;

    private static final String DEFAULT_LICNO = "AAAAAAAAAA";
    private static final String UPDATED_LICNO = "BBBBBBBBBB";

    private static final Long DEFAULT_NATID = 1L;
    private static final Long UPDATED_NATID = 2L;

    private static final Long DEFAULT_EDUCATION = 1L;
    private static final Long UPDATED_EDUCATION = 2L;

    private static final Long DEFAULT_CONSULTSRVID = 1L;
    private static final Long UPDATED_CONSULTSRVID = 2L;

    private static final Long DEFAULT_FOLLOWUPSRVID = 1L;
    private static final Long UPDATED_FOLLOWUPSRVID = 2L;

    private static final Boolean DEFAULT_EXTERNAL = false;
    private static final Boolean UPDATED_EXTERNAL = true;

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
    private DoctorRepository doctorRepository;

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

    private MockMvc restDoctorMockMvc;

    private Doctor doctor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DoctorResource doctorResource = new DoctorResource(doctorRepository);
        this.restDoctorMockMvc = MockMvcBuilders.standaloneSetup(doctorResource)
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
    public static Doctor createEntity(EntityManager em) {
        Doctor doctor = new Doctor()
            .orgid(DEFAULT_ORGID)
            .docid(DEFAULT_DOCID)
            .docname(DEFAULT_DOCNAME)
            .splid(DEFAULT_SPLID)
            .licno(DEFAULT_LICNO)
            .natid(DEFAULT_NATID)
            .education(DEFAULT_EDUCATION)
            .consultsrvid(DEFAULT_CONSULTSRVID)
            .followupsrvid(DEFAULT_FOLLOWUPSRVID)
            .external(DEFAULT_EXTERNAL)
            .defunct(DEFAULT_DEFUNCT)
            .creaton(DEFAULT_CREATON)
            .creatby(DEFAULT_CREATBY)
            .modifyon(DEFAULT_MODIFYON)
            .modifyby(DEFAULT_MODIFYBY);
        return doctor;
    }

    @BeforeEach
    public void initTest() {
        doctor = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoctor() throws Exception {
        int databaseSizeBeforeCreate = doctorRepository.findAll().size();

        // Create the Doctor
        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isCreated());

        // Validate the Doctor in the database
        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeCreate + 1);
        Doctor testDoctor = doctorList.get(doctorList.size() - 1);
        assertThat(testDoctor.getOrgid()).isEqualTo(DEFAULT_ORGID);
        assertThat(testDoctor.getDocid()).isEqualTo(DEFAULT_DOCID);
        assertThat(testDoctor.getDocname()).isEqualTo(DEFAULT_DOCNAME);
        assertThat(testDoctor.getSplid()).isEqualTo(DEFAULT_SPLID);
        assertThat(testDoctor.getLicno()).isEqualTo(DEFAULT_LICNO);
        assertThat(testDoctor.getNatid()).isEqualTo(DEFAULT_NATID);
        assertThat(testDoctor.getEducation()).isEqualTo(DEFAULT_EDUCATION);
        assertThat(testDoctor.getConsultsrvid()).isEqualTo(DEFAULT_CONSULTSRVID);
        assertThat(testDoctor.getFollowupsrvid()).isEqualTo(DEFAULT_FOLLOWUPSRVID);
        assertThat(testDoctor.isExternal()).isEqualTo(DEFAULT_EXTERNAL);
        assertThat(testDoctor.isDefunct()).isEqualTo(DEFAULT_DEFUNCT);
        assertThat(testDoctor.getCreaton()).isEqualTo(DEFAULT_CREATON);
        assertThat(testDoctor.getCreatby()).isEqualTo(DEFAULT_CREATBY);
        assertThat(testDoctor.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
        assertThat(testDoctor.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
    }

    @Test
    @Transactional
    public void createDoctorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doctorRepository.findAll().size();

        // Create the Doctor with an existing ID
        doctor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        // Validate the Doctor in the database
        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgidIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setOrgid(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDocidIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setDocid(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDocnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setDocname(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSplidIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setSplid(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLicnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setLicno(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExternalIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setExternal(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefunctIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setDefunct(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatonIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setCreaton(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatbyIsRequired() throws Exception {
        int databaseSizeBeforeTest = doctorRepository.findAll().size();
        // set the field null
        doctor.setCreatby(null);

        // Create the Doctor, which fails.

        restDoctorMockMvc.perform(post("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDoctors() throws Exception {
        // Initialize the database
        doctorRepository.saveAndFlush(doctor);

        // Get all the doctorList
        restDoctorMockMvc.perform(get("/api/doctors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doctor.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgid").value(hasItem(DEFAULT_ORGID.intValue())))
            .andExpect(jsonPath("$.[*].docid").value(hasItem(DEFAULT_DOCID.intValue())))
            .andExpect(jsonPath("$.[*].docname").value(hasItem(DEFAULT_DOCNAME.toString())))
            .andExpect(jsonPath("$.[*].splid").value(hasItem(DEFAULT_SPLID.intValue())))
            .andExpect(jsonPath("$.[*].licno").value(hasItem(DEFAULT_LICNO.toString())))
            .andExpect(jsonPath("$.[*].natid").value(hasItem(DEFAULT_NATID.intValue())))
            .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION.intValue())))
            .andExpect(jsonPath("$.[*].consultsrvid").value(hasItem(DEFAULT_CONSULTSRVID.intValue())))
            .andExpect(jsonPath("$.[*].followupsrvid").value(hasItem(DEFAULT_FOLLOWUPSRVID.intValue())))
            .andExpect(jsonPath("$.[*].external").value(hasItem(DEFAULT_EXTERNAL.booleanValue())))
            .andExpect(jsonPath("$.[*].defunct").value(hasItem(DEFAULT_DEFUNCT.booleanValue())))
            .andExpect(jsonPath("$.[*].creaton").value(hasItem(DEFAULT_CREATON.toString())))
            .andExpect(jsonPath("$.[*].creatby").value(hasItem(DEFAULT_CREATBY.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY.intValue())));
    }
    
    @Test
    @Transactional
    public void getDoctor() throws Exception {
        // Initialize the database
        doctorRepository.saveAndFlush(doctor);

        // Get the doctor
        restDoctorMockMvc.perform(get("/api/doctors/{id}", doctor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doctor.getId().intValue()))
            .andExpect(jsonPath("$.orgid").value(DEFAULT_ORGID.intValue()))
            .andExpect(jsonPath("$.docid").value(DEFAULT_DOCID.intValue()))
            .andExpect(jsonPath("$.docname").value(DEFAULT_DOCNAME.toString()))
            .andExpect(jsonPath("$.splid").value(DEFAULT_SPLID.intValue()))
            .andExpect(jsonPath("$.licno").value(DEFAULT_LICNO.toString()))
            .andExpect(jsonPath("$.natid").value(DEFAULT_NATID.intValue()))
            .andExpect(jsonPath("$.education").value(DEFAULT_EDUCATION.intValue()))
            .andExpect(jsonPath("$.consultsrvid").value(DEFAULT_CONSULTSRVID.intValue()))
            .andExpect(jsonPath("$.followupsrvid").value(DEFAULT_FOLLOWUPSRVID.intValue()))
            .andExpect(jsonPath("$.external").value(DEFAULT_EXTERNAL.booleanValue()))
            .andExpect(jsonPath("$.defunct").value(DEFAULT_DEFUNCT.booleanValue()))
            .andExpect(jsonPath("$.creaton").value(DEFAULT_CREATON.toString()))
            .andExpect(jsonPath("$.creatby").value(DEFAULT_CREATBY.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDoctor() throws Exception {
        // Get the doctor
        restDoctorMockMvc.perform(get("/api/doctors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoctor() throws Exception {
        // Initialize the database
        doctorRepository.saveAndFlush(doctor);

        int databaseSizeBeforeUpdate = doctorRepository.findAll().size();

        // Update the doctor
        Doctor updatedDoctor = doctorRepository.findById(doctor.getId()).get();
        // Disconnect from session so that the updates on updatedDoctor are not directly saved in db
        em.detach(updatedDoctor);
        updatedDoctor
            .orgid(UPDATED_ORGID)
            .docid(UPDATED_DOCID)
            .docname(UPDATED_DOCNAME)
            .splid(UPDATED_SPLID)
            .licno(UPDATED_LICNO)
            .natid(UPDATED_NATID)
            .education(UPDATED_EDUCATION)
            .consultsrvid(UPDATED_CONSULTSRVID)
            .followupsrvid(UPDATED_FOLLOWUPSRVID)
            .external(UPDATED_EXTERNAL)
            .defunct(UPDATED_DEFUNCT)
            .creaton(UPDATED_CREATON)
            .creatby(UPDATED_CREATBY)
            .modifyon(UPDATED_MODIFYON)
            .modifyby(UPDATED_MODIFYBY);

        restDoctorMockMvc.perform(put("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDoctor)))
            .andExpect(status().isOk());

        // Validate the Doctor in the database
        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeUpdate);
        Doctor testDoctor = doctorList.get(doctorList.size() - 1);
        assertThat(testDoctor.getOrgid()).isEqualTo(UPDATED_ORGID);
        assertThat(testDoctor.getDocid()).isEqualTo(UPDATED_DOCID);
        assertThat(testDoctor.getDocname()).isEqualTo(UPDATED_DOCNAME);
        assertThat(testDoctor.getSplid()).isEqualTo(UPDATED_SPLID);
        assertThat(testDoctor.getLicno()).isEqualTo(UPDATED_LICNO);
        assertThat(testDoctor.getNatid()).isEqualTo(UPDATED_NATID);
        assertThat(testDoctor.getEducation()).isEqualTo(UPDATED_EDUCATION);
        assertThat(testDoctor.getConsultsrvid()).isEqualTo(UPDATED_CONSULTSRVID);
        assertThat(testDoctor.getFollowupsrvid()).isEqualTo(UPDATED_FOLLOWUPSRVID);
        assertThat(testDoctor.isExternal()).isEqualTo(UPDATED_EXTERNAL);
        assertThat(testDoctor.isDefunct()).isEqualTo(UPDATED_DEFUNCT);
        assertThat(testDoctor.getCreaton()).isEqualTo(UPDATED_CREATON);
        assertThat(testDoctor.getCreatby()).isEqualTo(UPDATED_CREATBY);
        assertThat(testDoctor.getModifyon()).isEqualTo(UPDATED_MODIFYON);
        assertThat(testDoctor.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
    }

    @Test
    @Transactional
    public void updateNonExistingDoctor() throws Exception {
        int databaseSizeBeforeUpdate = doctorRepository.findAll().size();

        // Create the Doctor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoctorMockMvc.perform(put("/api/doctors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doctor)))
            .andExpect(status().isBadRequest());

        // Validate the Doctor in the database
        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoctor() throws Exception {
        // Initialize the database
        doctorRepository.saveAndFlush(doctor);

        int databaseSizeBeforeDelete = doctorRepository.findAll().size();

        // Delete the doctor
        restDoctorMockMvc.perform(delete("/api/doctors/{id}", doctor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Doctor> doctorList = doctorRepository.findAll();
        assertThat(doctorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Doctor.class);
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        Doctor doctor2 = new Doctor();
        doctor2.setId(doctor1.getId());
        assertThat(doctor1).isEqualTo(doctor2);
        doctor2.setId(2L);
        assertThat(doctor1).isNotEqualTo(doctor2);
        doctor1.setId(null);
        assertThat(doctor1).isNotEqualTo(doctor2);
    }
}
