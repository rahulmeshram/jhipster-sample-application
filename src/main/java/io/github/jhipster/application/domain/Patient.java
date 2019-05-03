package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "orgid", nullable = false)
    private Long orgid;

    @NotNull
    @Column(name = "patid", nullable = false)
    private String patid;

    @NotNull
    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "mname")
    private String mname;

    @NotNull
    @Column(name = "lname", nullable = false)
    private String lname;

    @Column(name = "dob")
    private Instant dob;

    @Column(name = "age_y")
    private Integer ageY;

    @Column(name = "age_m")
    private Integer ageM;

    @Column(name = "age_d")
    private Integer ageD;

    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "add_1")
    private String add1;

    @Column(name = "add_2")
    private String add2;

    @NotNull
    @Column(name = "cityid", nullable = false)
    private String cityid;

    @NotNull
    @Column(name = "stateid", nullable = false)
    private String stateid;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "postalcode", nullable = false)
    private String postalcode;

    @NotNull
    @Column(name = "natid", nullable = false)
    private Long natid;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "mobile_2")
    private String mobile2;

    @Column(name = "married")
    private String married;

    @Column(name = "occupationid")
    private Long occupationid;

    @Column(name = "idtype")
    private String idtype;

    @Column(name = "idnumber")
    private String idnumber;

    @NotNull
    @Column(name = "defunct", nullable = false)
    private Boolean defunct;

    @NotNull
    @Column(name = "creaton", nullable = false)
    private Instant creaton;

    @NotNull
    @Column(name = "creatby", nullable = false)
    private Long creatby;

    @Column(name = "modifyon")
    private Instant modifyon;

    @Column(name = "modifyby")
    private Long modifyby;

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GeneralMst> cityids = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GeneralMst> stateids = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GeneralMst> natids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgid() {
        return orgid;
    }

    public Patient orgid(Long orgid) {
        this.orgid = orgid;
        return this;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getPatid() {
        return patid;
    }

    public Patient patid(String patid) {
        this.patid = patid;
        return this;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getFname() {
        return fname;
    }

    public Patient fname(String fname) {
        this.fname = fname;
        return this;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public Patient mname(String mname) {
        this.mname = mname;
        return this;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public Patient lname(String lname) {
        this.lname = lname;
        return this;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Instant getDob() {
        return dob;
    }

    public Patient dob(Instant dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public Integer getAgeY() {
        return ageY;
    }

    public Patient ageY(Integer ageY) {
        this.ageY = ageY;
        return this;
    }

    public void setAgeY(Integer ageY) {
        this.ageY = ageY;
    }

    public Integer getAgeM() {
        return ageM;
    }

    public Patient ageM(Integer ageM) {
        this.ageM = ageM;
        return this;
    }

    public void setAgeM(Integer ageM) {
        this.ageM = ageM;
    }

    public Integer getAgeD() {
        return ageD;
    }

    public Patient ageD(Integer ageD) {
        this.ageD = ageD;
        return this;
    }

    public void setAgeD(Integer ageD) {
        this.ageD = ageD;
    }

    public String getGender() {
        return gender;
    }

    public Patient gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdd1() {
        return add1;
    }

    public Patient add1(String add1) {
        this.add1 = add1;
        return this;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public Patient add2(String add2) {
        this.add2 = add2;
        return this;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getCityid() {
        return cityid;
    }

    public Patient cityid(String cityid) {
        this.cityid = cityid;
        return this;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getStateid() {
        return stateid;
    }

    public Patient stateid(String stateid) {
        this.stateid = stateid;
        return this;
    }

    public void setStateid(String stateid) {
        this.stateid = stateid;
    }

    public String getCountry() {
        return country;
    }

    public Patient country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Patient postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public Long getNatid() {
        return natid;
    }

    public Patient natid(Long natid) {
        this.natid = natid;
        return this;
    }

    public void setNatid(Long natid) {
        this.natid = natid;
    }

    public String getEmail() {
        return email;
    }

    public Patient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public Patient mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public Patient mobile2(String mobile2) {
        this.mobile2 = mobile2;
        return this;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getMarried() {
        return married;
    }

    public Patient married(String married) {
        this.married = married;
        return this;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public Long getOccupationid() {
        return occupationid;
    }

    public Patient occupationid(Long occupationid) {
        this.occupationid = occupationid;
        return this;
    }

    public void setOccupationid(Long occupationid) {
        this.occupationid = occupationid;
    }

    public String getIdtype() {
        return idtype;
    }

    public Patient idtype(String idtype) {
        this.idtype = idtype;
        return this;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public Patient idnumber(String idnumber) {
        this.idnumber = idnumber;
        return this;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Boolean isDefunct() {
        return defunct;
    }

    public Patient defunct(Boolean defunct) {
        this.defunct = defunct;
        return this;
    }

    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    public Instant getCreaton() {
        return creaton;
    }

    public Patient creaton(Instant creaton) {
        this.creaton = creaton;
        return this;
    }

    public void setCreaton(Instant creaton) {
        this.creaton = creaton;
    }

    public Long getCreatby() {
        return creatby;
    }

    public Patient creatby(Long creatby) {
        this.creatby = creatby;
        return this;
    }

    public void setCreatby(Long creatby) {
        this.creatby = creatby;
    }

    public Instant getModifyon() {
        return modifyon;
    }

    public Patient modifyon(Instant modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(Instant modifyon) {
        this.modifyon = modifyon;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public Patient modifyby(Long modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Set<GeneralMst> getCityids() {
        return cityids;
    }

    public Patient cityids(Set<GeneralMst> generalMsts) {
        this.cityids = generalMsts;
        return this;
    }

    public Patient addCityid(GeneralMst generalMst) {
        this.cityids.add(generalMst);
        generalMst.setPatient(this);
        return this;
    }

    public Patient removeCityid(GeneralMst generalMst) {
        this.cityids.remove(generalMst);
        generalMst.setPatient(null);
        return this;
    }

    public void setCityids(Set<GeneralMst> generalMsts) {
        this.cityids = generalMsts;
    }

    public Set<GeneralMst> getStateids() {
        return stateids;
    }

    public Patient stateids(Set<GeneralMst> generalMsts) {
        this.stateids = generalMsts;
        return this;
    }

    public Patient addStateid(GeneralMst generalMst) {
        this.stateids.add(generalMst);
        generalMst.setPatient(this);
        return this;
    }

    public Patient removeStateid(GeneralMst generalMst) {
        this.stateids.remove(generalMst);
        generalMst.setPatient(null);
        return this;
    }

    public void setStateids(Set<GeneralMst> generalMsts) {
        this.stateids = generalMsts;
    }

    public Set<GeneralMst> getNatids() {
        return natids;
    }

    public Patient natids(Set<GeneralMst> generalMsts) {
        this.natids = generalMsts;
        return this;
    }

    public Patient addNatid(GeneralMst generalMst) {
        this.natids.add(generalMst);
        generalMst.setPatient(this);
        return this;
    }

    public Patient removeNatid(GeneralMst generalMst) {
        this.natids.remove(generalMst);
        generalMst.setPatient(null);
        return this;
    }

    public void setNatids(Set<GeneralMst> generalMsts) {
        this.natids = generalMsts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", orgid=" + getOrgid() +
            ", patid='" + getPatid() + "'" +
            ", fname='" + getFname() + "'" +
            ", mname='" + getMname() + "'" +
            ", lname='" + getLname() + "'" +
            ", dob='" + getDob() + "'" +
            ", ageY=" + getAgeY() +
            ", ageM=" + getAgeM() +
            ", ageD=" + getAgeD() +
            ", gender='" + getGender() + "'" +
            ", add1='" + getAdd1() + "'" +
            ", add2='" + getAdd2() + "'" +
            ", cityid='" + getCityid() + "'" +
            ", stateid='" + getStateid() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalcode='" + getPostalcode() + "'" +
            ", natid=" + getNatid() +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", mobile2='" + getMobile2() + "'" +
            ", married='" + getMarried() + "'" +
            ", occupationid=" + getOccupationid() +
            ", idtype='" + getIdtype() + "'" +
            ", idnumber='" + getIdnumber() + "'" +
            ", defunct='" + isDefunct() + "'" +
            ", creaton='" + getCreaton() + "'" +
            ", creatby=" + getCreatby() +
            ", modifyon='" + getModifyon() + "'" +
            ", modifyby=" + getModifyby() +
            "}";
    }
}
