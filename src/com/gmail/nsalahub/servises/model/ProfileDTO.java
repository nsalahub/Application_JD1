package com.gmail.nsalahub.servises.model;


import com.gmail.nsalahub.reposetory.model.UserRegistration;

public class ProfileDTO {
    private UserRegistrationDTO userDTO;
    private String address;
    private String telephone;

    public UserRegistrationDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserRegistrationDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
