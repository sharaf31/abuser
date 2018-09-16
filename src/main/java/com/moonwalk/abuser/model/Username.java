package com.moonwalk.abuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class  Username implements Serializable {
    private String username;
}
