package med.voll.api.dto;

import med.voll.api.entity.DoctorEntity;

public record DoctorDetailDTO(
        Long id,
        String name,
        String crm,
        String email,
        String phone,
        SpecialityDTO speciality,
        AddressDetailDTO address
) {
    public DoctorDetailDTO(DoctorEntity doctor) {
        this (
                doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpeciality(),
                doctor.getAddress() != null ? new AddressDetailDTO(doctor.getAddress()) : null
        );
    }
}
