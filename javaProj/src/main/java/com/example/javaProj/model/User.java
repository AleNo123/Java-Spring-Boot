package com.example.javaProj.model;

import com.example.javaProj.Enum.AccessRights;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
@Entity
@Table(name = "\"user\"")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "user-with-courses",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "courses",
                                subgraph = "course-name"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "course-name",
                                attributeNodes = {
                                        @NamedAttributeNode("title")
                                }
                        )
                }
        ),
        @NamedEntityGraph(name = "user-entity-graph")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
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
    @Column(name = "email_address")
    private String emailAddress;
    @NotBlank
    private String pathToUserIcon;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateRegistration;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_changer_id")
    private User UserChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User UserDeleter;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Course> courses;
    private String accessRights;

    public User() {
    }

    public User(String nickname, String name, String surname, String emailAddress) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserDeleter(User userDeleter) {
        UserDeleter = userDeleter;
    }

    public Long getUserId() {
        return userId;
    }

    public User getUserDeleter() {
        return UserDeleter;
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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPathToUserIcon(String pathToUserIcon) {
        this.pathToUserIcon = pathToUserIcon;
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

    public void setUsersCourses(Set<Course> usersCourses) {
        this.courses = usersCourses;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPathToUserIcon() {
        return pathToUserIcon;
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

    public Set<Course> getUsersCourses() {
        return courses;
    }

    public String getAccessRights() {
        return accessRights;
    }
}
