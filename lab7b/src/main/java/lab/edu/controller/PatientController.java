package lab.edu.controller;

import java.util.List;

import lab.edu.dto.address.AddressRequest;
import lab.edu.dto.patient.PatientRequest;
import lab.edu.dto.patient.PatientResponse;
import lab.edu.graphql.input.NewPatient;
import lab.edu.service.PatientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @QueryMapping("allPatients")
    public List<PatientResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    @QueryMapping("patientById")
    public PatientResponse getPatientById(@Argument Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @QueryMapping("searchPatients")
    public List<PatientResponse> searchPatients(@Argument String searchString) {
        return patientService.searchPatients(searchString);
    }

    @MutationMapping("addNewPatient")
    public PatientResponse registerNewPatient(@Argument NewPatient newPatient) {
        return patientService.addNewPatient(mapToPatientRequest(newPatient));
    }

    @MutationMapping("updatePatient")
    public PatientResponse updatePatient(@Argument Long patientId, @Argument NewPatient newPatient) {
        return patientService.updatePatient(patientId, mapToPatientRequest(newPatient));
    }

    @MutationMapping("deletePatient")
    public Boolean deletePatient(@Argument Long patientId) {
        patientService.deletePatient(patientId);
        return true;
    }

    private PatientRequest mapToPatientRequest(NewPatient newPatient) {
        AddressRequest addressRequest = new AddressRequest(
                newPatient.primaryAddress().city(),
                newPatient.primaryAddress().street(),
                newPatient.primaryAddress().houseNumber()
        );

        return new PatientRequest(
                newPatient.firstName(),
                newPatient.lastName(),
                newPatient.age(),
                addressRequest
        );
    }
}
