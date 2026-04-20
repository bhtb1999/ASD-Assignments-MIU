package lab.edu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lab.edu.dto.patient.PatientResponse;
import lab.edu.exception.PatientNotFoundException;
import lab.edu.model.Patient;
import lab.edu.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PatientServiceIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void shouldFindPatientByIdWhenPatientExists() {
        Patient existingPatient = patientRepository.findAllPatientsOrderByLastNameAscFirstNameAsc()
                .stream()
                .findFirst()
                .orElseThrow();

        PatientResponse patientResponse = patientService.findPatientById(existingPatient.getId());

        assertNotNull(patientResponse);
        assertEquals(existingPatient.getId(), patientResponse.patientId());
        assertEquals(existingPatient.getFName(), patientResponse.firstName());
        assertEquals(existingPatient.getLName(), patientResponse.lastName());
    }

    @Test
    void shouldThrowExceptionWhenPatientIdIsInvalid() {
        Long invalidPatientId = Long.MAX_VALUE;

        PatientNotFoundException exception = assertThrows(
                PatientNotFoundException.class,
                () -> patientService.findPatientById(invalidPatientId)
        );

        assertEquals("Patient with id " + invalidPatientId + " was not found", exception.getMessage());
    }
}
