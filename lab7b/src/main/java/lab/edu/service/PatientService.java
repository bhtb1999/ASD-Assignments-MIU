package lab.edu.service;

import java.util.List;

import lab.edu.dto.patient.PatientRequest;
import lab.edu.dto.patient.PatientResponse;

public interface PatientService {

    List<PatientResponse> getAllPatients();

    PatientResponse getPatientById(Long patientId);

    PatientResponse addNewPatient(PatientRequest patientRequest);

    PatientResponse updatePatient(Long patientId, PatientRequest patientRequest);

    void deletePatient(Long patientId);

    List<PatientResponse> searchPatients(String searchString);
}
