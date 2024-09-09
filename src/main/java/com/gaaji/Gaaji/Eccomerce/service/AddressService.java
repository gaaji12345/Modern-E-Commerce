package com.gaaji.Gaaji.Eccomerce.service;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.AddressDTO;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;

public interface AddressService {
    Responce saveAndUpdateAddress(AddressDTO addressDto);
}
