package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Table(name="ADDRESSES")
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

    private String city;

    private String state;

    private String country;

    public AddressEntity(AddressDTO dto) {
        this.postalCode = dto.postalCode();
        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
        this.city = dto.city();
        this.state = dto.state();
        this.country = dto.country();
    }
}
