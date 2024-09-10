package com.gaaji.Gaaji.Eccomerce.controller;/*  gaajiCode
    99
    10/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.AddressDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<Responce> saveAndUpdateAddress(@RequestBody AddressDTO addressDto){
        return ResponseEntity.ok(addressService.saveAndUpdateAddress(addressDto));
    }
}
