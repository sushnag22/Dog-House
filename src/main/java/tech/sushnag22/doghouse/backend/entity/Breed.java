package tech.sushnag22.doghouse.backend.entity;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "breed")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "findBreeds",
                procedureName = "findAllBreeds",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "BreedName",
                                type = String.class
                        )},
                resultClasses = { Breed.class }
        )
})
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "breed", cascade = CascadeType.MERGE)
    private Set<Dog> dog;

    public Breed() {

    }

    public Breed(String name) {
        this.name = name;
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

    public Set<Dog> getDog() {
        return dog;
    }

    public void setDog(Set<Dog> dog) {
        this.dog = dog;
    }
}