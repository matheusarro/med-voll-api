package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO (
        @NotNull
        Long id,

        String name,

        String phone,

        @Valid
        AddressUpdateDTO address
) {}
