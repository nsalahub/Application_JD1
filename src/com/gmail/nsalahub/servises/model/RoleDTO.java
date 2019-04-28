package com.gmail.nsalahub.servises.model;

import com.gmail.nsalahub.reposetory.model.PermissionEnum;

import java.util.List;

public class RoleDTO {

    private long id;
    private String name;
    private List<PermissionEnum> permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PermissionEnum> getPermission() {
        return permission;
    }

    public void setPermission(List<PermissionEnum> permission) {
        this.permission = permission;
    }
}
