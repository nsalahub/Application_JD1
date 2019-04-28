package com.gmail.nsalahub.reposetory.model;

import java.util.List;

public class Role {

    private Long id;
    private String name;
    private List<PermissionEnum> permissionEnums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionEnum> getPermissionEnums() {
        return permissionEnums;
    }

    public void setPermissionEnums(List<PermissionEnum> permissionEnums) {
        this.permissionEnums = permissionEnums;
    }
}
