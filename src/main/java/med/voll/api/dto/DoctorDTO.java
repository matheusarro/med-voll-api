package med.voll.api.dto;

public record DoctorDTO (
        String name,
        String crm,
        String email,
        SpecialityDTO speciality,
        AddressDTO address
) {}
