package lab.edu.dto.patient;

public record PatientSummaryResponse(
        Long patientId,
        String firstName,
        String lastName,
        int age
) {
}
