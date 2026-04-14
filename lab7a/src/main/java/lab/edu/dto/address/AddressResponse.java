package lab.edu.dto.address;

public record AddressResponse(
        Long addressId,
        String city,
        String street,
        int houseNumber
) {
}
