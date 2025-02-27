package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating", schema = "public", catalog = "rpdb_lab1")
public class RatingEntity {
    private int rId;
    private int rYear;
    private int rClassId;
    private int rSubjectId;
    private int rPointQuarter;
    private int rPointPartYear;
    private int rPointYear;
    private int rPointExam;
    private int rPointEnd;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "r_id", nullable = false)
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    @Basic
    @Column(name = "r_year_", nullable = true)
    public Integer getrYear() {
        return rYear;
    }

    public void setrYear(Integer rYear) {
        this.rYear = rYear;
    }

    @Basic
    @Column(name = "r_class_id", nullable = true)
    public Integer getrClassId() {
        return rClassId;
    }

    public void setrClassId(Integer rClassId) {
        this.rClassId = rClassId;
    }

    @Basic
    @Column(name = "r_subject_id", nullable = true)
    public Integer getrSubjectId() {
        return rSubjectId;
    }

    public void setrSubjectId(Integer rSubjectId) {
        this.rSubjectId = rSubjectId;
    }

    @Basic
    @Column(name = "r_point_quarter", nullable = true)
    public Integer getrPointQuarter() {
        return rPointQuarter;
    }

    public void setrPointQuarter(Integer rPointQuarter) {
        this.rPointQuarter = rPointQuarter;
    }

    @Basic
    @Column(name = "r_point_part_year", nullable = true)
    public Integer getrPointPartYear() {
        return rPointPartYear;
    }

    public void setrPointPartYear(Integer rPointPartYear) {
        this.rPointPartYear = rPointPartYear;
    }

    @Basic
    @Column(name = "r_point_year", nullable = true)
    public Integer getrPointYear() {
        return rPointYear;
    }

    public void setrPointYear(Integer rPointYear) {
        this.rPointYear = rPointYear;
    }

    @Basic
    @Column(name = "r_point_exam", nullable = true)
    public Integer getrPointExam() {
        return rPointExam;
    }

    public void setrPointExam(Integer rPointExam) {
        this.rPointExam = rPointExam;
    }

    @Basic
    @Column(name = "r_point_end", nullable = true)
    public Integer getrPointEnd() {
        return rPointEnd;
    }

    public void setrPointEnd(Integer rPointEnd) {
        this.rPointEnd = rPointEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingEntity that = (RatingEntity) o;
        return rId == that.rId && Objects.equals(rYear, that.rYear) && Objects.equals(rClassId, that.rClassId) && Objects.equals(rSubjectId, that.rSubjectId) && Objects.equals(rPointQuarter, that.rPointQuarter) && Objects.equals(rPointPartYear, that.rPointPartYear) && Objects.equals(rPointYear, that.rPointYear) && Objects.equals(rPointExam, that.rPointExam) && Objects.equals(rPointEnd, that.rPointEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rId, rYear, rClassId, rSubjectId, rPointQuarter, rPointPartYear, rPointYear, rPointExam, rPointEnd);
    }
}
