package lab.edu.controller;

import java.util.List;

import lab.edu.dto.patient.PatientRequest;
import lab.edu.dto.patient.PatientResponse;
import lab.edu.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patients/{patientId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PostMapping("/patients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> registerNewPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.addNewPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long patientId,
                                                         @Valid @RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, patientRequest));
    }

    @DeleteMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/search/{searchString}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PatientResponse>> searchPatients(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatients(searchString));
    }
}
