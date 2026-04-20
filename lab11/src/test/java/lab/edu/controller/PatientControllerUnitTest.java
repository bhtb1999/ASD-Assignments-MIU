package lab.edu.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import lab.edu.advice.RestApiExceptionHandler;
import lab.edu.dto.address.AddressResponse;
import lab.edu.dto.patient.PatientResponse;
import lab.edu.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PatientControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController)
                .setControllerAdvice(new RestApiExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnAllPatients() throws Exception {
        List<PatientResponse> patients = List.of(
                new PatientResponse(1L, "Gillian", "White", 30, new AddressResponse(1L, "Glasgow", "Sauchiehall St", 10)),
                new PatientResponse(2L, "Jill", "Bell", 28, new AddressResponse(2L, "Glasgow", "Buchanan St", 20))
        );
        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Gillian"))
                .andExpect(jsonPath("$[0].lastName").value("White"))
                .andExpect(jsonPath("$[1].patientId").value(2))
                .andExpect(jsonPath("$[1].primaryAddress.city").value("Glasgow"));
    }
}
