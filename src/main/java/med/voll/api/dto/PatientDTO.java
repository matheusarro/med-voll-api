package med.voll.api.dto;

public record PatientDTO(
        String name,
        String email,
        String phone,
        String cpf,
        AddressDTO address
) {}
