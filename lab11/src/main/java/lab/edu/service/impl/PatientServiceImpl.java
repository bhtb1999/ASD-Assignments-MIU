package lab.edu.service.impl;

import java.util.List;

import lab.edu.dto.address.AddressRequest;
import lab.edu.dto.address.AddressResponse;
import lab.edu.dto.patient.PatientRequest;
import lab.edu.dto.patient.PatientResponse;
import lab.edu.exception.PatientNotFoundException;
import lab.edu.model.Address;
import lab.edu.model.Patient;
import lab.edu.repository.AddressRepository;
import lab.edu.repository.PatientRepository;
import lab.edu.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAllPatientsOrderByLastNameAscFirstNameAsc()
                .stream()
                .map(this::mapToPatientResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse findPatientById(Long patientId) {
        return mapToPatientResponse(findPatientEntityById(patientId));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long patientId) {
        return findPatientById(patientId);
    }

    @Override
    public PatientResponse addNewPatient(PatientRequest patientRequest) {
        Address address = addressRepository.save(mapToAddress(patientRequest.primaryAddress()));
        Patient patient = new Patient(null,
                patientRequest.firstName(),
                patientRequest.lastName(),
                patientRequest.age(),
                address);
        return mapToPatientResponse(patientRepository.save(patient));
    }

    @Override
    public PatientResponse updatePatient(Long patientId, PatientRequest patientRequest) {
        Patient patient = findPatientEntityById(patientId);
        patient.setFName(patientRequest.firstName());
        patient.setLName(patientRequest.lastName());
        patient.setAge(patientRequest.age());

        Address address = patient.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setCity(patientRequest.primaryAddress().city());
        address.setStreet(patientRequest.primaryAddress().street());
        address.setHouseNumber(patientRequest.primaryAddress().houseNumber());
        patient.setAddress(addressRepository.save(address));

        return mapToPatientResponse(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = findPatientEntityById(patientId);
        patientRepository.delete(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> searchPatients(String searchString) {
        return patientRepository.searchPatients(searchString)
                .stream()
                .sorted((left, right) -> {
                    int lastNameCompare = left.getLName().compareToIgnoreCase(right.getLName());
                    if (lastNameCompare != 0) {
                        return lastNameCompare;
                    }
                    return left.getFName().compareToIgnoreCase(right.getFName());
                })
                .map(this::mapToPatientResponse)
                .toList();
    }

    private Patient findPatientEntityById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    private PatientResponse mapToPatientResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getFName(),
                patient.getLName(),
                patient.getAge(),
                mapToAddressResponse(patient.getAddress())
        );
    }

    private AddressResponse mapToAddressResponse(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressResponse(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getHouseNumber()
        );
    }

    private Address mapToAddress(AddressRequest addressRequest) {
        return new Address(
                null,
                addressRequest.city(),
                addressRequest.street(),
                addressRequest.houseNumber()
        );
    }
}
