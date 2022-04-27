package com.example.restservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private int id;

    @NotEmpty(message = "name must not be empty")
    @NotNull(message = "name is required")
    @NotBlank(message = "name must not be blank")
    private String name;
    private String address;
    private short postnr;
    private String place;
    private int mobile;
    private String email;
    private String birthDate;
    private boolean marketingAllowed;

}
