package com.example.test2.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "public", catalog = "rpdb_lab1")
public class AddressEntity {
    private int adId;
    private String adName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ad_id", nullable = false)
    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    @Basic
    @Column(name = "ad_name", nullable = true, length = -1)
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return adId == that.adId && Objects.equals(adName, that.adName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, adName);
    }
}
