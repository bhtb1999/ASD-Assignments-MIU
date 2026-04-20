package lab.edu.controller;

import java.util.List;

import lab.edu.dto.address.AddressWithPatientsResponse;
import lab.edu.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<AddressWithPatientsResponse>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
}
