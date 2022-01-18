package tech.sushnag22.doghouse.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "colour")
    private String colour;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Dog() {

    }

    public Dog(String name, Date birthDate, String gender, String colour, String description, String location) {
        this.name = name;
        this.birthDate=birthDate;
        this.gender=gender;
        this.colour=colour;
        this.description=description;
        this.location=location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void  setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColour() {
       return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String description) {
        this.description = description;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Dog [id = " + id + ", name = " + name + ", gender = " + gender + ", colour = " + colour + ", description = " + description + ", location = " + location + "]";
    }
}
