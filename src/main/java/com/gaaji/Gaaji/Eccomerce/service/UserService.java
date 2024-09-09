package com.gaaji.Gaaji.Eccomerce.service;/*  gaajiCode
    99
    09/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.LoginRequest;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.dto.UserDTO;
import com.gaaji.Gaaji.Eccomerce.entity.User;

public interface UserService {
    Responce registerUser(UserDTO registrationRequest);
    Responce loginUser(LoginRequest loginRequest);
    Responce getAllUsers();
    User getLoginUser();
    Responce getUserInfoAndOrderHistory();
}
