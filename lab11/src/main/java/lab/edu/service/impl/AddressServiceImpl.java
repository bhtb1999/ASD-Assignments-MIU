package lab.edu.service.impl;

import java.util.List;

import lab.edu.dto.address.AddressWithPatientsResponse;
import lab.edu.dto.patient.PatientSummaryResponse;
import lab.edu.model.Address;
import lab.edu.repository.AddressRepository;
import lab.edu.repository.PatientRepository;
import lab.edu.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    public AddressServiceImpl(AddressRepository addressRepository, PatientRepository patientRepository) {
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<AddressWithPatientsResponse> getAllAddresses() {
        return addressRepository.findAllByOrderByCityAscStreetAscHouseNumberAsc()
                .stream()
                .map(this::mapToAddressResponse)
                .toList();
    }

    private AddressWithPatientsResponse mapToAddressResponse(Address address) {
        List<PatientSummaryResponse> patients = patientRepository.findPatientsByAddressId(address.getId())
                .stream()
                .map(patient -> new PatientSummaryResponse(
                        patient.getId(),
                        patient.getFName(),
                        patient.getLName(),
                        patient.getAge()
                ))
                .toList();

        return new AddressWithPatientsResponse(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getHouseNumber(),
                patients
        );
    }
}
