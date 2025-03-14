package com.example.javaProj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "profile")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "login", length = 200, nullable = false)
    private String login;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_registered", nullable = false)
    private OffsetDateTime dateRegistered;

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Profile(Long profileId, String login, OffsetDateTime dateRegistered) {
        this.profileId = profileId;
        this.login = login;
        this.dateRegistered = dateRegistered;
    }
    public Profile() {
    }

    public void setDateRegistered(OffsetDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getLogin() {
        return login;
    }

    public OffsetDateTime getDateRegistered() {
        return dateRegistered;
    } // по хорошему надо будет возвращать в нормальном виде
}