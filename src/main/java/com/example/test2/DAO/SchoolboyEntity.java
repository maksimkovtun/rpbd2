package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "schoolboy", schema = "public", catalog = "rpdb_lab1")
public class SchoolboyEntity {
    private int sId;
    private Integer sPId;
    private Integer sRId;
    private String sSurname;
    private String sFirstName;
    private String sSurname2;
    private Integer sAddressId;
    private Integer sBirthDate;
    private Integer sClassId;
    private Integer sAdmYear;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "s_id", nullable = false)
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "s_p_id", nullable = true)
    public Integer getsPId() {
        return sPId;
    }

    public void setsPId(Integer sPId) {
        this.sPId = sPId;
    }

    @Basic
    @Column(name = "s_r_id", nullable = true)
    public Integer getsRId() {
        return sRId;
    }

    public void setsRId(Integer sRId) {
        this.sRId = sRId;
    }

    @Basic
    @Column(name = "s_surname", nullable = true, length = -1)
    public String getsSurname() {
        return sSurname;
    }

    public void setsSurname(String sSurname) {
        this.sSurname = sSurname;
    }

    @Basic
    @Column(name = "s_first_name", nullable = true, length = -1)
    public String getsFirstName() {
        return sFirstName;
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    @Basic
    @Column(name = "s_surname_2", nullable = true, length = -1)
    public String getsSurname2() {
        return sSurname2;
    }

    public void setsSurname2(String sSurname2) {
        this.sSurname2 = sSurname2;
    }

    @Basic
    @Column(name = "s_address_id", nullable = true)
    public Integer getsAddressId() {
        return sAddressId;
    }

    public void setsAddressId(Integer sAddressId) {
        this.sAddressId = sAddressId;
    }

    @Basic
    @Column(name = "s_birth_date", nullable = true)
    public Integer getsBirthDate() {
        return sBirthDate;
    }

    public void setsBirthDate(Integer sBirthDate) {
        this.sBirthDate = sBirthDate;
    }

    @Basic
    @Column(name = "s_class_id", nullable = true)
    public Integer getsClassId() {
        return sClassId;
    }

    public void setsClassId(Integer sClassId) {
        this.sClassId = sClassId;
    }

    @Basic
    @Column(name = "s_adm_year", nullable = true)
    public Integer getsAdmYear() {
        return sAdmYear;
    }

    public void setsAdmYear(Integer sAdmYear) {
        this.sAdmYear = sAdmYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolboyEntity that = (SchoolboyEntity) o;
        return sId == that.sId && Objects.equals(sPId, that.sPId) && Objects.equals(sRId, that.sRId) && Objects.equals(sSurname, that.sSurname) && Objects.equals(sFirstName, that.sFirstName) && Objects.equals(sSurname2, that.sSurname2) && Objects.equals(sAddressId, that.sAddressId) && Objects.equals(sBirthDate, that.sBirthDate) && Objects.equals(sClassId, that.sClassId) && Objects.equals(sAdmYear, that.sAdmYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sId, sPId, sRId, sSurname, sFirstName, sSurname2, sAddressId, sBirthDate, sClassId, sAdmYear);
    }
}
