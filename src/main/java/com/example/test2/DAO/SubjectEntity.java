package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "public", catalog = "rpdb_lab1")
public class SubjectEntity {
    private int suId;
    private String suName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "su_id", nullable = false)
    public int getSuId() {
        return suId;
    }

    public void setSuId(int suId) {
        this.suId = suId;
    }

    @Basic
    @Column(name = "su_name", nullable = true, length = -1)
    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return suId == that.suId && Objects.equals(suName, that.suName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suId, suName);
    }
}
