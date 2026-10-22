package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.*;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorCreateDTO doctor) {
        AddressEntity address = addressRepository.save(new AddressEntity(doctor.address()));
        doctorRepository.save(new DoctorEntity(doctor, address));
    }

    @GetMapping
    public Page<DoctorsListDTO> getList(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        return doctorRepository
                .findAll(pagination)
                .map(DoctorsListDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> get(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .map(DoctorDetailDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctor) {
        var doctorAddress = doctor.address();
        var doctorEntity = doctorRepository.getReferenceById(id);

        if (doctor.address() != null) {
            var addressEntity = addressRepository.getReferenceById(doctorEntity.getAddress().getId());
            addressEntity.update(doctorAddress);
        }

        doctorEntity.update(doctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctorEntity = doctorRepository.getReferenceById(id);
        doctorEntity.delete();
    }
}
