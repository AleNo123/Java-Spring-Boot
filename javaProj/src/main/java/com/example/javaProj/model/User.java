package com.example.javaProj.model;

import com.example.javaProj.Enum.AccessRights;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotNull
    @Size(min = 4, max = 25)
    private String nickname;
    @NotNull
    @Size(min = 3,max = 30)
    private String name;
    @NotNull
    @Size(min = 3,max = 30)
    private String surname;
    @NotNull
    @Email(message = "Invalid email format")
    private String emailAdress;

    @Lob
    private byte[] userIcon;

    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateRegistration;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;

    private User UserChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting; //????

    @OneToMany(fetch = FetchType.LAZY)
    private List<Course> usersCourses;
    private String accessRights;

    public User() {
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public void setUserIcon(byte[] userIcon) {
        this.userIcon = userIcon;
    }

    public void setDateRegistration(OffsetDateTime dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public void setDateChanging(OffsetDateTime dateChanging) {
        this.dateChanging = dateChanging;
    }

    public void setUserChanger(User userChanger) {
        UserChanger = userChanger;
    }

    public void setDateDeleting(OffsetDateTime dateDeleting) {
        this.dateDeleting = dateDeleting;
    }

    public void setUsersCourses(List<Course> usersCourses) {
        this.usersCourses = usersCourses;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public Long getId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public byte[] getUserIcon() {
        return userIcon;
    }

    public OffsetDateTime getDateRegistration() {
        return dateRegistration;
    }

    public OffsetDateTime getDateChanging() {
        return dateChanging;
    }

    public User getUserChanger() {
        return UserChanger;
    }

    public OffsetDateTime getDateDeleting() {
        return dateDeleting;
    }

    public List<Course> getUsersCourses() {
        return usersCourses;
    }

    public String getAccessRights() {
        return accessRights;
    }
}
