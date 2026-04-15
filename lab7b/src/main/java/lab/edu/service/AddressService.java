package lab.edu.service;

import java.util.List;

import lab.edu.dto.address.AddressWithPatientsResponse;

public interface AddressService {

    List<AddressWithPatientsResponse> getAllAddresses();
}
