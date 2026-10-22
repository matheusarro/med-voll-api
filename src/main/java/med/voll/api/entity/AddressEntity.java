package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressCreateDTO;
import med.voll.api.dto.AddressUpdateDTO;

@Table(name="addresses")
@Entity(name="Address")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String postalCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String country;

    public AddressEntity(AddressCreateDTO dto) {
        this.postalCode = dto.postalCode();
        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
        this.neighborhood = dto.neighborhood();
        this.city = dto.city();
        this.state = dto.state();
        this.country = dto.country();
    }

    public void update(AddressUpdateDTO data) {
        this.postalCode = data.postalCode();
        this.street = data.street();
        this.number = data.number();
        this.complement = data.complement();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
        this.country = data.country();
    }
}
