package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.dto.SpecialityDTO;

@Table(name="doctors")
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

    private String phone;

    @Enumerated(EnumType.STRING)
    private SpecialityDTO speciality;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    private boolean active;

    public DoctorEntity(DoctorDTO doctor, AddressEntity address) {
        this.name = doctor.name();
        this.crm = doctor.crm();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.speciality = doctor.speciality();
        this.address = address;
        this.active = true;
    }

    public void update(DoctorUpdateDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }
    }

    public void delete() {
        this.active = false;
    }
}
