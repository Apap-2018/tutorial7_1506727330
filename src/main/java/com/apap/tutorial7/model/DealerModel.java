package com.apap.tutorial7.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="dealer")
public class DealerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 13)
    @Column(name = "no_telp", nullable = false)
    private String noTelp;

    @OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<CarModel> listCar;

    public void setId(long id) {
        this.id = id;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setListCar(List<CarModel> listCar) {
        this.listCar = listCar;
    }

    public long getId() {
        return id;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public List<CarModel> getListCar() {
        return listCar;
    }
}
