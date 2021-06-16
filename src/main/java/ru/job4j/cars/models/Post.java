package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String desc;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idCar")
    private Car car;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "photoId")
    private PhotoCar photoCar;

    public Post(){
    }

    public Post(String desc, Date created, Car car, PhotoCar photoCar) {
        this.desc = desc;
        this.created = created;
        this.car = car;
        this.photoCar = photoCar;
    }

    public Post(Integer id, String desc, Date created, Car car) {
        this.id = id;
        this.desc = desc;
        this.created = created;
        this.car = car;
    }

    public Post(String desc, Date created, Car car) {
        this.desc = desc;
        this.created = created;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date creared) {
        this.created = creared;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public PhotoCar getPhotoCar() {
        return photoCar;
    }

    public void setPhotoCar(PhotoCar photoCar) {
        this.photoCar = photoCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(desc, post.desc) && Objects.equals(created, post.created) && Objects.equals(car, post.car) && Objects.equals(photoCar, post.photoCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, created, car, photoCar);
    }

    @Override
    public String toString() {
        return "\nPost{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", creared=" + created +
                '}';
    }
}
