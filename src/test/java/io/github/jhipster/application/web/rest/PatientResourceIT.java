package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Patient;
import io.github.jhipster.application.repository.PatientRepository;
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
 * Integration tests for the {@Link PatientResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PatientResourceIT {

    private static final Long DEFAULT_ORGID = 1L;
    private static final Long UPDATED_ORGID = 2L;

    private static final String DEFAULT_PATID = "AAAAAAAAAA";
    private static final String UPDATED_PATID = "BBBBBBBBBB";

    private static final String DEFAULT_FNAME = "AAAAAAAAAA";
    private static final String UPDATED_FNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MNAME = "AAAAAAAAAA";
    private static final String UPDATED_MNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LNAME = "AAAAAAAAAA";
    private static final String UPDATED_LNAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_AGE_Y = 1;
    private static final Integer UPDATED_AGE_Y = 2;

    private static final Integer DEFAULT_AGE_M = 1;
    private static final Integer UPDATED_AGE_M = 2;

    private static final Integer DEFAULT_AGE_D = 1;
    private static final Integer UPDATED_AGE_D = 2;

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITYID = "AAAAAAAAAA";
    private static final String UPDATED_CITYID = "BBBBBBBBBB";

    private static final String DEFAULT_STATEID = "AAAAAAAAAA";
    private static final String UPDATED_STATEID = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTALCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_NATID = 1L;
    private static final Long UPDATED_NATID = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_2 = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MARRIED = "AAAAAAAAAA";
    private static final String UPDATED_MARRIED = "BBBBBBBBBB";

    private static final Long DEFAULT_OCCUPATIONID = 1L;
    private static final Long UPDATED_OCCUPATIONID = 2L;

    private static final String DEFAULT_IDTYPE = "AAAAAAAAAA";
    private static final String UPDATED_IDTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_IDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_IDNUMBER = "BBBBBBBBBB";

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
    private PatientRepository patientRepository;

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

    private MockMvc restPatientMockMvc;

    private Patient patient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PatientResource patientResource = new PatientResource(patientRepository);
        this.restPatientMockMvc = MockMvcBuilders.standaloneSetup(patientResource)
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
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .orgid(DEFAULT_ORGID)
            .patid(DEFAULT_PATID)
            .fname(DEFAULT_FNAME)
            .mname(DEFAULT_MNAME)
            .lname(DEFAULT_LNAME)
            .dob(DEFAULT_DOB)
            .ageY(DEFAULT_AGE_Y)
            .ageM(DEFAULT_AGE_M)
            .ageD(DEFAULT_AGE_D)
            .gender(DEFAULT_GENDER)
            .add1(DEFAULT_ADD_1)
            .add2(DEFAULT_ADD_2)
            .cityid(DEFAULT_CITYID)
            .stateid(DEFAULT_STATEID)
            .country(DEFAULT_COUNTRY)
            .postalcode(DEFAULT_POSTALCODE)
            .natid(DEFAULT_NATID)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .mobile2(DEFAULT_MOBILE_2)
            .married(DEFAULT_MARRIED)
            .occupationid(DEFAULT_OCCUPATIONID)
            .idtype(DEFAULT_IDTYPE)
            .idnumber(DEFAULT_IDNUMBER)
            .defunct(DEFAULT_DEFUNCT)
            .creaton(DEFAULT_CREATON)
            .creatby(DEFAULT_CREATBY)
            .modifyon(DEFAULT_MODIFYON)
            .modifyby(DEFAULT_MODIFYBY);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getOrgid()).isEqualTo(DEFAULT_ORGID);
        assertThat(testPatient.getPatid()).isEqualTo(DEFAULT_PATID);
        assertThat(testPatient.getFname()).isEqualTo(DEFAULT_FNAME);
        assertThat(testPatient.getMname()).isEqualTo(DEFAULT_MNAME);
        assertThat(testPatient.getLname()).isEqualTo(DEFAULT_LNAME);
        assertThat(testPatient.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPatient.getAgeY()).isEqualTo(DEFAULT_AGE_Y);
        assertThat(testPatient.getAgeM()).isEqualTo(DEFAULT_AGE_M);
        assertThat(testPatient.getAgeD()).isEqualTo(DEFAULT_AGE_D);
        assertThat(testPatient.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPatient.getAdd1()).isEqualTo(DEFAULT_ADD_1);
        assertThat(testPatient.getAdd2()).isEqualTo(DEFAULT_ADD_2);
        assertThat(testPatient.getCityid()).isEqualTo(DEFAULT_CITYID);
        assertThat(testPatient.getStateid()).isEqualTo(DEFAULT_STATEID);
        assertThat(testPatient.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPatient.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testPatient.getNatid()).isEqualTo(DEFAULT_NATID);
        assertThat(testPatient.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPatient.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testPatient.getMobile2()).isEqualTo(DEFAULT_MOBILE_2);
        assertThat(testPatient.getMarried()).isEqualTo(DEFAULT_MARRIED);
        assertThat(testPatient.getOccupationid()).isEqualTo(DEFAULT_OCCUPATIONID);
        assertThat(testPatient.getIdtype()).isEqualTo(DEFAULT_IDTYPE);
        assertThat(testPatient.getIdnumber()).isEqualTo(DEFAULT_IDNUMBER);
        assertThat(testPatient.isDefunct()).isEqualTo(DEFAULT_DEFUNCT);
        assertThat(testPatient.getCreaton()).isEqualTo(DEFAULT_CREATON);
        assertThat(testPatient.getCreatby()).isEqualTo(DEFAULT_CREATBY);
        assertThat(testPatient.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
        assertThat(testPatient.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
    }

    @Test
    @Transactional
    public void createPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient with an existing ID
        patient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrgidIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setOrgid(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPatidIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setPatid(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setFname(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setLname(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setGender(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityidIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCityid(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateidIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setStateid(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCountry(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPostalcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setPostalcode(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNatidIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setNatid(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setMobile(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefunctIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setDefunct(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatonIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCreaton(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatbyIsRequired() throws Exception {
        int databaseSizeBeforeTest = patientRepository.findAll().size();
        // set the field null
        patient.setCreatby(null);

        // Create the Patient, which fails.

        restPatientMockMvc.perform(post("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].orgid").value(hasItem(DEFAULT_ORGID.intValue())))
            .andExpect(jsonPath("$.[*].patid").value(hasItem(DEFAULT_PATID.toString())))
            .andExpect(jsonPath("$.[*].fname").value(hasItem(DEFAULT_FNAME.toString())))
            .andExpect(jsonPath("$.[*].mname").value(hasItem(DEFAULT_MNAME.toString())))
            .andExpect(jsonPath("$.[*].lname").value(hasItem(DEFAULT_LNAME.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].ageY").value(hasItem(DEFAULT_AGE_Y)))
            .andExpect(jsonPath("$.[*].ageM").value(hasItem(DEFAULT_AGE_M)))
            .andExpect(jsonPath("$.[*].ageD").value(hasItem(DEFAULT_AGE_D)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].add1").value(hasItem(DEFAULT_ADD_1.toString())))
            .andExpect(jsonPath("$.[*].add2").value(hasItem(DEFAULT_ADD_2.toString())))
            .andExpect(jsonPath("$.[*].cityid").value(hasItem(DEFAULT_CITYID.toString())))
            .andExpect(jsonPath("$.[*].stateid").value(hasItem(DEFAULT_STATEID.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].postalcode").value(hasItem(DEFAULT_POSTALCODE.toString())))
            .andExpect(jsonPath("$.[*].natid").value(hasItem(DEFAULT_NATID.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].mobile2").value(hasItem(DEFAULT_MOBILE_2.toString())))
            .andExpect(jsonPath("$.[*].married").value(hasItem(DEFAULT_MARRIED.toString())))
            .andExpect(jsonPath("$.[*].occupationid").value(hasItem(DEFAULT_OCCUPATIONID.intValue())))
            .andExpect(jsonPath("$.[*].idtype").value(hasItem(DEFAULT_IDTYPE.toString())))
            .andExpect(jsonPath("$.[*].idnumber").value(hasItem(DEFAULT_IDNUMBER.toString())))
            .andExpect(jsonPath("$.[*].defunct").value(hasItem(DEFAULT_DEFUNCT.booleanValue())))
            .andExpect(jsonPath("$.[*].creaton").value(hasItem(DEFAULT_CREATON.toString())))
            .andExpect(jsonPath("$.[*].creatby").value(hasItem(DEFAULT_CREATBY.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY.intValue())));
    }
    
    @Test
    @Transactional
    public void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().intValue()))
            .andExpect(jsonPath("$.orgid").value(DEFAULT_ORGID.intValue()))
            .andExpect(jsonPath("$.patid").value(DEFAULT_PATID.toString()))
            .andExpect(jsonPath("$.fname").value(DEFAULT_FNAME.toString()))
            .andExpect(jsonPath("$.mname").value(DEFAULT_MNAME.toString()))
            .andExpect(jsonPath("$.lname").value(DEFAULT_LNAME.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.ageY").value(DEFAULT_AGE_Y))
            .andExpect(jsonPath("$.ageM").value(DEFAULT_AGE_M))
            .andExpect(jsonPath("$.ageD").value(DEFAULT_AGE_D))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.add1").value(DEFAULT_ADD_1.toString()))
            .andExpect(jsonPath("$.add2").value(DEFAULT_ADD_2.toString()))
            .andExpect(jsonPath("$.cityid").value(DEFAULT_CITYID.toString()))
            .andExpect(jsonPath("$.stateid").value(DEFAULT_STATEID.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.postalcode").value(DEFAULT_POSTALCODE.toString()))
            .andExpect(jsonPath("$.natid").value(DEFAULT_NATID.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.mobile2").value(DEFAULT_MOBILE_2.toString()))
            .andExpect(jsonPath("$.married").value(DEFAULT_MARRIED.toString()))
            .andExpect(jsonPath("$.occupationid").value(DEFAULT_OCCUPATIONID.intValue()))
            .andExpect(jsonPath("$.idtype").value(DEFAULT_IDTYPE.toString()))
            .andExpect(jsonPath("$.idnumber").value(DEFAULT_IDNUMBER.toString()))
            .andExpect(jsonPath("$.defunct").value(DEFAULT_DEFUNCT.booleanValue()))
            .andExpect(jsonPath("$.creaton").value(DEFAULT_CREATON.toString()))
            .andExpect(jsonPath("$.creatby").value(DEFAULT_CREATBY.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .orgid(UPDATED_ORGID)
            .patid(UPDATED_PATID)
            .fname(UPDATED_FNAME)
            .mname(UPDATED_MNAME)
            .lname(UPDATED_LNAME)
            .dob(UPDATED_DOB)
            .ageY(UPDATED_AGE_Y)
            .ageM(UPDATED_AGE_M)
            .ageD(UPDATED_AGE_D)
            .gender(UPDATED_GENDER)
            .add1(UPDATED_ADD_1)
            .add2(UPDATED_ADD_2)
            .cityid(UPDATED_CITYID)
            .stateid(UPDATED_STATEID)
            .country(UPDATED_COUNTRY)
            .postalcode(UPDATED_POSTALCODE)
            .natid(UPDATED_NATID)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .mobile2(UPDATED_MOBILE_2)
            .married(UPDATED_MARRIED)
            .occupationid(UPDATED_OCCUPATIONID)
            .idtype(UPDATED_IDTYPE)
            .idnumber(UPDATED_IDNUMBER)
            .defunct(UPDATED_DEFUNCT)
            .creaton(UPDATED_CREATON)
            .creatby(UPDATED_CREATBY)
            .modifyon(UPDATED_MODIFYON)
            .modifyby(UPDATED_MODIFYBY);

        restPatientMockMvc.perform(put("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPatient)))
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getOrgid()).isEqualTo(UPDATED_ORGID);
        assertThat(testPatient.getPatid()).isEqualTo(UPDATED_PATID);
        assertThat(testPatient.getFname()).isEqualTo(UPDATED_FNAME);
        assertThat(testPatient.getMname()).isEqualTo(UPDATED_MNAME);
        assertThat(testPatient.getLname()).isEqualTo(UPDATED_LNAME);
        assertThat(testPatient.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPatient.getAgeY()).isEqualTo(UPDATED_AGE_Y);
        assertThat(testPatient.getAgeM()).isEqualTo(UPDATED_AGE_M);
        assertThat(testPatient.getAgeD()).isEqualTo(UPDATED_AGE_D);
        assertThat(testPatient.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPatient.getAdd1()).isEqualTo(UPDATED_ADD_1);
        assertThat(testPatient.getAdd2()).isEqualTo(UPDATED_ADD_2);
        assertThat(testPatient.getCityid()).isEqualTo(UPDATED_CITYID);
        assertThat(testPatient.getStateid()).isEqualTo(UPDATED_STATEID);
        assertThat(testPatient.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPatient.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testPatient.getNatid()).isEqualTo(UPDATED_NATID);
        assertThat(testPatient.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPatient.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testPatient.getMobile2()).isEqualTo(UPDATED_MOBILE_2);
        assertThat(testPatient.getMarried()).isEqualTo(UPDATED_MARRIED);
        assertThat(testPatient.getOccupationid()).isEqualTo(UPDATED_OCCUPATIONID);
        assertThat(testPatient.getIdtype()).isEqualTo(UPDATED_IDTYPE);
        assertThat(testPatient.getIdnumber()).isEqualTo(UPDATED_IDNUMBER);
        assertThat(testPatient.isDefunct()).isEqualTo(UPDATED_DEFUNCT);
        assertThat(testPatient.getCreaton()).isEqualTo(UPDATED_CREATON);
        assertThat(testPatient.getCreatby()).isEqualTo(UPDATED_CREATBY);
        assertThat(testPatient.getModifyon()).isEqualTo(UPDATED_MODIFYON);
        assertThat(testPatient.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
    }

    @Test
    @Transactional
    public void updateNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Create the Patient

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc.perform(put("/api/patients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(patient)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc.perform(delete("/api/patients/{id}", patient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Patient.class);
        Patient patient1 = new Patient();
        patient1.setId(1L);
        Patient patient2 = new Patient();
        patient2.setId(patient1.getId());
        assertThat(patient1).isEqualTo(patient2);
        patient2.setId(2L);
        assertThat(patient1).isNotEqualTo(patient2);
        patient1.setId(null);
        assertThat(patient1).isNotEqualTo(patient2);
    }
}
