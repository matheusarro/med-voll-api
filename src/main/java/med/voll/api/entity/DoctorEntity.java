package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.dto.SpecialityDTO;

@Table(name="DOCTORS")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String crm;

    private String email;

    @Enumerated(EnumType.STRING)
    private SpecialityDTO speciality;

    private Long addressId;

    public DoctorEntity(DoctorDTO doctor) {
        this.name = doctor.name();
        this.crm = doctor.crm();
        this.email = doctor.email();
        this.speciality = doctor.speciality();
    }
}
