package med.voll.api.dto;

public record DoctorDTO (String name, String email, String crm, SpecialityDTO speciality, AddressDTO address) {

}
