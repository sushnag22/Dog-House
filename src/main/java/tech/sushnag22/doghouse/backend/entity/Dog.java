package tech.sushnag22.doghouse.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "colour")
    private String colour;

    @Column(name = "description")
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(mappedBy = "dog", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Adoption adoption;

    public Dog() {

    }

    public Dog(String name, LocalDate birthDate, String gender, String colour, String description, String location) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void  setBirthDate(LocalDate birthDate) {
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

    public void setLocation(String location) {
        this.location = location;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Users getUsers() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }
}