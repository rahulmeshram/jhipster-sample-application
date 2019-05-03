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
 * A GeneralMst.
 */
@Entity
@Table(name = "general_mst")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeneralMst implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "orgid", nullable = false)
    private Long orgid;

    @NotNull
    @Column(name = "genid", nullable = false)
    private String genid;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

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

    @OneToMany(mappedBy = "cityid")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Patient> genids = new HashSet<>();

    @OneToMany(mappedBy = "stateid")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Patient> genids = new HashSet<>();

    @OneToMany(mappedBy = "natid")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Patient> genids = new HashSet<>();

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

    public GeneralMst orgid(Long orgid) {
        this.orgid = orgid;
        return this;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getGenid() {
        return genid;
    }

    public GeneralMst genid(String genid) {
        this.genid = genid;
        return this;
    }

    public void setGenid(String genid) {
        this.genid = genid;
    }

    public String getType() {
        return type;
    }

    public GeneralMst type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public GeneralMst name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDefunct() {
        return defunct;
    }

    public GeneralMst defunct(Boolean defunct) {
        this.defunct = defunct;
        return this;
    }

    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    public Instant getCreaton() {
        return creaton;
    }

    public GeneralMst creaton(Instant creaton) {
        this.creaton = creaton;
        return this;
    }

    public void setCreaton(Instant creaton) {
        this.creaton = creaton;
    }

    public Long getCreatby() {
        return creatby;
    }

    public GeneralMst creatby(Long creatby) {
        this.creatby = creatby;
        return this;
    }

    public void setCreatby(Long creatby) {
        this.creatby = creatby;
    }

    public Instant getModifyon() {
        return modifyon;
    }

    public GeneralMst modifyon(Instant modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(Instant modifyon) {
        this.modifyon = modifyon;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public GeneralMst modifyby(Long modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Set<Patient> getGenids() {
        return genids;
    }

    public GeneralMst genids(Set<Patient> patients) {
        this.genids = patients;
        return this;
    }

    public GeneralMst addGenid(Patient patient) {
        this.genids.add(patient);
        patient.setCityid(this);
        return this;
    }

    public GeneralMst removeGenid(Patient patient) {
        this.genids.remove(patient);
        patient.setCityid(null);
        return this;
    }

    public void setGenids(Set<Patient> patients) {
        this.genids = patients;
    }

    public Set<Patient> getGenids() {
        return genids;
    }

    public GeneralMst genids(Set<Patient> patients) {
        this.genids = patients;
        return this;
    }

    public GeneralMst addGenid(Patient patient) {
        this.genids.add(patient);
        patient.setStateid(this);
        return this;
    }

    public GeneralMst removeGenid(Patient patient) {
        this.genids.remove(patient);
        patient.setStateid(null);
        return this;
    }

    public void setGenids(Set<Patient> patients) {
        this.genids = patients;
    }

    public Set<Patient> getGenids() {
        return genids;
    }

    public GeneralMst genids(Set<Patient> patients) {
        this.genids = patients;
        return this;
    }

    public GeneralMst addGenid(Patient patient) {
        this.genids.add(patient);
        patient.setNatid(this);
        return this;
    }

    public GeneralMst removeGenid(Patient patient) {
        this.genids.remove(patient);
        patient.setNatid(null);
        return this;
    }

    public void setGenids(Set<Patient> patients) {
        this.genids = patients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralMst)) {
            return false;
        }
        return id != null && id.equals(((GeneralMst) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GeneralMst{" +
            "id=" + getId() +
            ", orgid=" + getOrgid() +
            ", genid='" + getGenid() + "'" +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", defunct='" + isDefunct() + "'" +
            ", creaton='" + getCreaton() + "'" +
            ", creatby=" + getCreatby() +
            ", modifyon='" + getModifyon() + "'" +
            ", modifyby=" + getModifyby() +
            "}";
    }
}
