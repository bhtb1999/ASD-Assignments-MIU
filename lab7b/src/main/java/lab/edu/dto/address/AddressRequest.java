package lab.edu.dto.address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "Street is required")
        String street,
        @Min(value = 1, message = "House number must be greater than 0")
        int houseNumber
) {
}
