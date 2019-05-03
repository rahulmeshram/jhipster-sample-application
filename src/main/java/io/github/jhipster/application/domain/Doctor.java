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
 * A Doctor.
 */
@Entity
@Table(name = "doctor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "orgid", nullable = false)
    private Long orgid;

    @NotNull
    @Column(name = "docid", nullable = false)
    private Long docid;

    @NotNull
    @Column(name = "docname", nullable = false)
    private String docname;

    @NotNull
    @Column(name = "splid", nullable = false)
    private Long splid;

    @NotNull
    @Column(name = "licno", nullable = false)
    private String licno;

    @Column(name = "natid")
    private Long natid;

    @Column(name = "education")
    private Long education;

    @Column(name = "consultsrvid")
    private Long consultsrvid;

    @Column(name = "followupsrvid")
    private Long followupsrvid;

    @NotNull
    @Column(name = "jhi_external", nullable = false)
    private Boolean external;

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

    @OneToMany(mappedBy = "doctor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Specility> splids = new HashSet<>();

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

    public Doctor orgid(Long orgid) {
        this.orgid = orgid;
        return this;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getDocid() {
        return docid;
    }

    public Doctor docid(Long docid) {
        this.docid = docid;
        return this;
    }

    public void setDocid(Long docid) {
        this.docid = docid;
    }

    public String getDocname() {
        return docname;
    }

    public Doctor docname(String docname) {
        this.docname = docname;
        return this;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public Long getSplid() {
        return splid;
    }

    public Doctor splid(Long splid) {
        this.splid = splid;
        return this;
    }

    public void setSplid(Long splid) {
        this.splid = splid;
    }

    public String getLicno() {
        return licno;
    }

    public Doctor licno(String licno) {
        this.licno = licno;
        return this;
    }

    public void setLicno(String licno) {
        this.licno = licno;
    }

    public Long getNatid() {
        return natid;
    }

    public Doctor natid(Long natid) {
        this.natid = natid;
        return this;
    }

    public void setNatid(Long natid) {
        this.natid = natid;
    }

    public Long getEducation() {
        return education;
    }

    public Doctor education(Long education) {
        this.education = education;
        return this;
    }

    public void setEducation(Long education) {
        this.education = education;
    }

    public Long getConsultsrvid() {
        return consultsrvid;
    }

    public Doctor consultsrvid(Long consultsrvid) {
        this.consultsrvid = consultsrvid;
        return this;
    }

    public void setConsultsrvid(Long consultsrvid) {
        this.consultsrvid = consultsrvid;
    }

    public Long getFollowupsrvid() {
        return followupsrvid;
    }

    public Doctor followupsrvid(Long followupsrvid) {
        this.followupsrvid = followupsrvid;
        return this;
    }

    public void setFollowupsrvid(Long followupsrvid) {
        this.followupsrvid = followupsrvid;
    }

    public Boolean isExternal() {
        return external;
    }

    public Doctor external(Boolean external) {
        this.external = external;
        return this;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Boolean isDefunct() {
        return defunct;
    }

    public Doctor defunct(Boolean defunct) {
        this.defunct = defunct;
        return this;
    }

    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    public Instant getCreaton() {
        return creaton;
    }

    public Doctor creaton(Instant creaton) {
        this.creaton = creaton;
        return this;
    }

    public void setCreaton(Instant creaton) {
        this.creaton = creaton;
    }

    public Long getCreatby() {
        return creatby;
    }

    public Doctor creatby(Long creatby) {
        this.creatby = creatby;
        return this;
    }

    public void setCreatby(Long creatby) {
        this.creatby = creatby;
    }

    public Instant getModifyon() {
        return modifyon;
    }

    public Doctor modifyon(Instant modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(Instant modifyon) {
        this.modifyon = modifyon;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public Doctor modifyby(Long modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Set<Specility> getSplids() {
        return splids;
    }

    public Doctor splids(Set<Specility> specilities) {
        this.splids = specilities;
        return this;
    }

    public Doctor addSplid(Specility specility) {
        this.splids.add(specility);
        specility.setDoctor(this);
        return this;
    }

    public Doctor removeSplid(Specility specility) {
        this.splids.remove(specility);
        specility.setDoctor(null);
        return this;
    }

    public void setSplids(Set<Specility> specilities) {
        this.splids = specilities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doctor)) {
            return false;
        }
        return id != null && id.equals(((Doctor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "id=" + getId() +
            ", orgid=" + getOrgid() +
            ", docid=" + getDocid() +
            ", docname='" + getDocname() + "'" +
            ", splid=" + getSplid() +
            ", licno='" + getLicno() + "'" +
            ", natid=" + getNatid() +
            ", education=" + getEducation() +
            ", consultsrvid=" + getConsultsrvid() +
            ", followupsrvid=" + getFollowupsrvid() +
            ", external='" + isExternal() + "'" +
            ", defunct='" + isDefunct() + "'" +
            ", creaton='" + getCreaton() + "'" +
            ", creatby=" + getCreatby() +
            ", modifyon='" + getModifyon() + "'" +
            ", modifyby=" + getModifyby() +
            "}";
    }
}
