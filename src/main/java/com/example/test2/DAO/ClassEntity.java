package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class_", schema = "public", catalog = "rpdb_lab1")
public class ClassEntity {
    private int clId;
    private String clName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cl_id", nullable = false)
    public int getClId() {
        return clId;
    }

    public void setClId(int clId) {
        this.clId = clId;
    }

    @Basic
    @Column(name = "cl_name", nullable = true, length = -1)
    public String getClName() {
        return clName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return clId == that.clId && Objects.equals(clName, that.clName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clId, clName);
    }
}
