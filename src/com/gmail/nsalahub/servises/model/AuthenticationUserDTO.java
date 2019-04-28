package com.gmail.nsalahub.servises.model;

import com.gmail.nsalahub.reposetory.model.PermissionEnum;

public class AuthenticationUserDTO {


    PermissionEnum permissionEnum;

    public PermissionEnum getPermissionEnum() {
        return permissionEnum;
    }

    public void setPermissionEnum(PermissionEnum permissionEnum) {
        this.permissionEnum = permissionEnum;
    }
}

