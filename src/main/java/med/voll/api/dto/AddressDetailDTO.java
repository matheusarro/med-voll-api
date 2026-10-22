package med.voll.api.dto;

import med.voll.api.entity.AddressEntity;

public record AddressDetailDTO(
        Long id,
        String postalCode,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String country
) {
    public AddressDetailDTO(AddressEntity address) {
        this(
                address.getId(),
                address.getPostalCode(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }
}
