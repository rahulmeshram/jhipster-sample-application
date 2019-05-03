package io.github.jhipster.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Specility.
 */
@Entity
@Table(name = "specility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Specility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "orgid", nullable = false)
    private Long orgid;

    @NotNull
    @Column(name = "splid", nullable = false)
    private Long splid;

    @NotNull
    @Column(name = "splname", nullable = false)
    private String splname;

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

    @ManyToOne
    @JsonIgnoreProperties("specilities")
    private Doctor doctor;

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

    public Specility orgid(Long orgid) {
        this.orgid = orgid;
        return this;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getSplid() {
        return splid;
    }

    public Specility splid(Long splid) {
        this.splid = splid;
        return this;
    }

    public void setSplid(Long splid) {
        this.splid = splid;
    }

    public String getSplname() {
        return splname;
    }

    public Specility splname(String splname) {
        this.splname = splname;
        return this;
    }

    public void setSplname(String splname) {
        this.splname = splname;
    }

    public Boolean isDefunct() {
        return defunct;
    }

    public Specility defunct(Boolean defunct) {
        this.defunct = defunct;
        return this;
    }

    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    public Instant getCreaton() {
        return creaton;
    }

    public Specility creaton(Instant creaton) {
        this.creaton = creaton;
        return this;
    }

    public void setCreaton(Instant creaton) {
        this.creaton = creaton;
    }

    public Long getCreatby() {
        return creatby;
    }

    public Specility creatby(Long creatby) {
        this.creatby = creatby;
        return this;
    }

    public void setCreatby(Long creatby) {
        this.creatby = creatby;
    }

    public Instant getModifyon() {
        return modifyon;
    }

    public Specility modifyon(Instant modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(Instant modifyon) {
        this.modifyon = modifyon;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public Specility modifyby(Long modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Specility doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specility)) {
            return false;
        }
        return id != null && id.equals(((Specility) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Specility{" +
            "id=" + getId() +
            ", orgid=" + getOrgid() +
            ", splid=" + getSplid() +
            ", splname='" + getSplname() + "'" +
            ", defunct='" + isDefunct() + "'" +
            ", creaton='" + getCreaton() + "'" +
            ", creatby=" + getCreatby() +
            ", modifyon='" + getModifyon() + "'" +
            ", modifyby=" + getModifyby() +
            "}";
    }
}
