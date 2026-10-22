package med.voll.api.service;

import med.voll.api.dto.*;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void create(DoctorCreateDTO doctor) {
        AddressEntity address = addressRepository.save(new AddressEntity(doctor.address()));
        doctorRepository.save(new DoctorEntity(doctor, address));
    }

    public Page<DoctorsListDTO> getList(Pageable pagination) {
        return doctorRepository.findAll(pagination).map(DoctorsListDTO::new);
    }

    public DoctorDetailDTO get(Long id) {
        return doctorRepository.findById(id)
                .map(DoctorDetailDTO::new)
                .orElse(null);
    }

    @Transactional
    public void update(Long id, DoctorUpdateDTO doctor) {
        var doctorEntity = doctorRepository.getReferenceById(id);

        if (doctor.address() != null) {
            var addressEntity = addressRepository.getReferenceById(doctorEntity.getAddress().getId());
            addressEntity.update(doctor.address());
        }

        doctorEntity.update(doctor);
    }

    @Transactional
    public void delete(Long id) {
        var doctorEntity = doctorRepository.getReferenceById(id);
        doctorEntity.delete();
    }
}