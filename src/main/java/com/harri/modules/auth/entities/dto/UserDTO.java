package com.harri.modules.auth.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    private String username;
    private String password;

    // Construtores
    public UserDTO() {}

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters e setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
