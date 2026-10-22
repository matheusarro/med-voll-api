package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.*;

@Table(name="patients")
@Entity(name="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    private boolean active;

    public PatientEntity(PatientCreateDTO patient, AddressEntity address) {
        this.name = patient.name();
        this.cpf = patient.cpf();
        this.email = patient.email();
        this.phone = patient.phone();
        this.address = address;
        this.active = true;
    }

    public void update(PatientUpdateDTO data) {
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
