package com.moonwalk.abuser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@JsonIgnoreProperties
@Builder
public class User {

    @Id
    @lombok.Generated
    private String userId;

    @Indexed(name = "username")
    private String username;

    private String password;

    private Boolean verified;

    private Boolean deleted = Boolean.FALSE;

    private String email;

    private String msisdn;

    private String profileId;

    private String callerId;

    private String firstName;

    private String lastName;

    @Builder.Default()
    private boolean active = true;

    @Builder.Default()
    private List<String> roles = new ArrayList<>();

}
