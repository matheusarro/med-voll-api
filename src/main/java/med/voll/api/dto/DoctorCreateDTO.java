package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorCreateDTO(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}", message = "CRM must contain only numbers and have between 4 and 6 digits")
        String crm,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain only numbers and have between 10 and 11 digits")
        String phone,

        @NotNull
        SpecialityDTO speciality,

        @NotNull
        @Valid
        AddressCreateDTO address
) {}
