package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientDTO(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "CPF must contain only numbers and have between 11 digits")
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain only numbers and have between 10 and 11 digits")
        String phone,

        @NotNull
        @Valid
        AddressDTO address
) {}
