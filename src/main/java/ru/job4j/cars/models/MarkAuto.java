package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "markAuto")
public class MarkAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public MarkAuto(){
    }

    public MarkAuto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MarkAuto(String name) {
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
        MarkAuto markAuto = (MarkAuto) o;
        return Objects.equals(id, markAuto.id) && Objects.equals(name, markAuto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkAuto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
