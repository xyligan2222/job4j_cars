package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo")
public class PhotoCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public PhotoCar(){
    }

    public PhotoCar(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoCar photoCar = (PhotoCar) o;
        return Objects.equals(id, photoCar.id) && Objects.equals(name, photoCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "\nPhotoCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
