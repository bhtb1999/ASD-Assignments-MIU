package lab.edu.controller;

import java.util.List;

import lab.edu.dto.address.AddressWithPatientsResponse;
import lab.edu.service.AddressService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @QueryMapping("allAddresses")
    public List<AddressWithPatientsResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }
}
