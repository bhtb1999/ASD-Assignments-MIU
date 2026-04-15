package lab.edu.dto.patient;

import lab.edu.dto.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @Min(value = 0, message = "Age cannot be negative")
        int age,
        @NotNull(message = "Primary address is required")
        @Valid
        AddressRequest primaryAddress
) {
}
