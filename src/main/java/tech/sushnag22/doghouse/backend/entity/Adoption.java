package tech.sushnag22.doghouse.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(
        name = "adoption",
        indexes = {
                @Index(name = "idxAdopter", columnList = "adopter_id"),
                @Index(name = "idxDog", columnList = "dog_id")
        }
)

@Entity
public class Adoption {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private LocalDateTime adoptionDate;

        @ManyToOne(cascade = CascadeType.ALL, optional = false)
        @JoinColumn(name = "adopter_id", referencedColumnName = "id", nullable = false)
        private Adopter adopter;

        @OneToOne(cascade = CascadeType.ALL, optional = false)
        @JoinColumn(name = "dog_id", nullable = false, unique = true)
        private Dog dog;

        public Adoption() {
        }

        public Adoption(LocalDateTime adoptionDate, Adopter adopter, Dog dog) {
                this.adoptionDate = adoptionDate;
                this.adopter = adopter;
                this.dog = dog;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public LocalDateTime getAdoptionDate() {
                return adoptionDate;
        }

        public void setAdoptionDate(LocalDateTime adoptionDate) {
                this.adoptionDate = adoptionDate;
        }

        public Adopter getAdopter() {
                return adopter;
        }

        public void setCustomer(Adopter adopter) {
                this.adopter = adopter;
        }

        public Dog getDog() {
                return dog;
        }

        public void setDog(Dog dog) {
                this.dog = dog;
        }

}