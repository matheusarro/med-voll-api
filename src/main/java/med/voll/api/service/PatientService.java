package med.voll.api.service;

import med.voll.api.dto.PatientCreateDTO;
import med.voll.api.dto.PatientDetailDTO;
import med.voll.api.dto.PatientUpdateDTO;
import med.voll.api.dto.PatientsListDTO;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public PatientDetailDTO create(PatientCreateDTO patient) {
        AddressEntity address = addressRepository.save(new AddressEntity(patient.address()));
        var patientEntity = patientRepository.save(new PatientEntity(patient, address));
        return new PatientDetailDTO(patientEntity);
    }

    public Page<PatientsListDTO> getList(Pageable pagination) {
        return patientRepository.findAll(pagination).map(PatientsListDTO::new);
    }

    public PatientDetailDTO get(Long id) {
        return patientRepository.findById(id)
                .map(PatientDetailDTO::new)
                .orElse(null);
    }

    @Transactional
    public PatientDetailDTO update(Long id, PatientUpdateDTO patient) {
        var patientEntity = patientRepository.getReferenceById(id);

        if (patient.address() != null) {
            var addressEntity = addressRepository.getReferenceById(patientEntity.getAddress().getId());
            addressEntity.update(patient.address());
        }

        patientEntity.update(patient);

        return new PatientDetailDTO(patientEntity);
    }

    @Transactional
    public void delete(Long id) {
        var patientEntity = patientRepository.getReferenceById(id);
        patientEntity.delete();
    }
}