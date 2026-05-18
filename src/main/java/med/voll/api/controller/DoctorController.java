package med.voll.api.controller;

import med.voll.api.dto.DoctorDTO;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    public void createDoctor(@RequestBody DoctorDTO doctor) {
        AddressEntity address = addressRepository.save(new AddressEntity(doctor.address()));
        repository.save(new DoctorEntity(doctor, address));
    }
}
