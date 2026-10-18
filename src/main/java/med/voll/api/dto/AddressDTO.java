package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "Postal code must be in the format 12345678")
        String postalCode,

        @NotBlank
        String street,

        String number,

        String complement,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country
) { }
