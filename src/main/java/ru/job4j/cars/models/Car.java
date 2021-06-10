package ru.job4j.cars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String desc;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "markAuto")
    private MarkAuto markAuto;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "carBody")
    private CarBody carBody;

    public Car(){

    }

    public Car(Integer id, String desc, User user, MarkAuto markAuto, CarBody carBody) {
        this.id = id;
        this.desc = desc;
        this.user = user;
        this.markAuto = markAuto;
        this.carBody = carBody;
    }

    public Car( String desc, User user, MarkAuto markAuto, CarBody carBody) {
        this.desc = desc;
        this.user = user;
        this.markAuto = markAuto;
        this.carBody = carBody;
    }

    public Car( String desc, User user, CarBody carBody) {
        this.desc = desc;
        this.user = user;
        this.carBody = carBody;
    }

    public Car(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Car(String desc) {
        this.desc = desc;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MarkAuto getMarkAuto() {
        return markAuto;
    }

    public void setMarkAuto(MarkAuto markAuto) {
        this.markAuto = markAuto;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(desc, car.desc) && Objects.equals(user, car.user) && Objects.equals(markAuto, car.markAuto) && Objects.equals(carBody, car.carBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, user, markAuto, carBody);
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
