package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parants", schema = "public", catalog = "rpdb_lab1")
public class ParantsEntity {
    private int pId;
    private String pSurname;
    private String pFirstName;
    private String pSurname2;
    private int pAddressId;
    private String pStatus;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_id", nullable = false)
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "p_surname", nullable = true, length = -1)
    public String getpSurname() {
        return pSurname;
    }

    public void setpSurname(String pSurname) {
        this.pSurname = pSurname;
    }

    @Basic
    @Column(name = "p_first_name", nullable = true, length = -1)
    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    @Basic
    @Column(name = "p_surname_2", nullable = true, length = -1)
    public String getpSurname2() {
        return pSurname2;
    }

    public void setpSurname2(String pSurname2) {
        this.pSurname2 = pSurname2;
    }

    @Basic
    @Column(name = "p_address_id", nullable = true)
    public Integer getpAddressId() {
        return pAddressId;
    }

    public void setpAddressId(Integer pAddressId) {
        this.pAddressId = pAddressId;
    }

    @Basic
    @Column(name = "p_status", nullable = true, length = -1)
    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParantsEntity that = (ParantsEntity) o;
        return pId == that.pId && Objects.equals(pSurname, that.pSurname) && Objects.equals(pFirstName, that.pFirstName) && Objects.equals(pSurname2, that.pSurname2) && Objects.equals(pAddressId, that.pAddressId) && Objects.equals(pStatus, that.pStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId, pSurname, pFirstName, pSurname2, pAddressId, pStatus);
    }
}
