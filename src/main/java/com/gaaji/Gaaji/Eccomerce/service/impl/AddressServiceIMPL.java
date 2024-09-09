package com.gaaji.Gaaji.Eccomerce.service.impl;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.AddressDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.entity.Address;
import com.gaaji.Gaaji.Eccomerce.entity.User;
import com.gaaji.Gaaji.Eccomerce.repo.AddressRepo;
import com.gaaji.Gaaji.Eccomerce.service.AddressService;
import com.gaaji.Gaaji.Eccomerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceIMPL  implements AddressService {

    private final AddressRepo addressRepo;
    private final UserService userService;
    @Override
    public Responce saveAndUpdateAddress(AddressDTO addressDto) {
        User user = userService.getLoginUser();
        Address address = user.getAddress();

        if (address == null){
            address = new Address();
            address.setUser(user);
        }
        if (addressDto.getStreet() != null) address.setStreet(addressDto.getStreet());
        if (addressDto.getCity() != null) address.setCity(addressDto.getCity());
        if (addressDto.getState() != null) address.setState(addressDto.getState());
        if (addressDto.getZipCode() != null) address.setZipCode(addressDto.getZipCode());
        if (addressDto.getCountry() != null) address.setCountry(addressDto.getCountry());

        addressRepo.save(address);

        String message = (user.getAddress() == null) ? "Address successfully created" : "Address successfully updated";
        return Responce.builder()
                .status(200)
                .message(message)
                .build();

    }
}
