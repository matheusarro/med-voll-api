package med.voll.api.service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.dto.DoctorCreateDTO;
import med.voll.api.dto.DoctorDetailDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.dto.DoctorsListDTO;
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
    public DoctorDetailDTO create(DoctorCreateDTO doctor) {
        AddressEntity address = addressRepository.save(new AddressEntity(doctor.address()));
        DoctorEntity doctorEntity = doctorRepository.save(new DoctorEntity(doctor, address));
        return new DoctorDetailDTO(doctorEntity);
    }

    public Page<DoctorsListDTO> getList(Pageable pagination) {
        return doctorRepository.findAll(pagination).map(DoctorsListDTO::new);
    }

    public DoctorDetailDTO get(Long id) {
        return doctorRepository.findById(id)
                .map(DoctorDetailDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado."));
    }

    @Transactional
    public DoctorDetailDTO update(Long id, DoctorUpdateDTO doctor) {
        DoctorEntity doctorEntity = doctorRepository.getReferenceById(id);

        if (doctor.address() != null) {
            AddressEntity addressEntity = addressRepository.getReferenceById(doctorEntity.getAddress().getId());
            addressEntity.update(doctor.address());
        }

        doctorEntity.update(doctor);

        return new DoctorDetailDTO(doctorEntity);
    }

    @Transactional
    public void delete(Long id) {
        DoctorEntity doctorEntity = doctorRepository.getReferenceById(id);
        doctorEntity.delete();
    }
}