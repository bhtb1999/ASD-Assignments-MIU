package lab.edu.dto.patient;

import lab.edu.dto.address.AddressResponse;

public record PatientResponse(
        Long patientId,
        String firstName,
        String lastName,
        int age,
        AddressResponse primaryAddress
) {
}
