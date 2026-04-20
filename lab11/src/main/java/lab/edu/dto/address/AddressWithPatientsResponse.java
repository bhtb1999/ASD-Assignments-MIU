package lab.edu.dto.address;

import java.util.List;

import lab.edu.dto.patient.PatientSummaryResponse;

public record AddressWithPatientsResponse(
        Long addressId,
        String city,
        String street,
        int houseNumber,
        List<PatientSummaryResponse> patients
) {
}
