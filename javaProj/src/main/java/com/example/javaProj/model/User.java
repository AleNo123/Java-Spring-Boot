package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

                        ),
                        @NamedAttributeNode("avatar")
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
        @NamedEntityGraph(
                name = "user-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode("avatar")
                })
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long userId;
    @NotNull
    @Size(min = 4, max = 25)
    private String nickname;
    @NotNull

    private String name;
    @NotNull

    private String surname;
    @NotNull
    @Email(message = "Invalid email format")
    @Column(name = "email_address")
    private String emailAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private File avatar; //---------------------------------------

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateRegistration;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_changer_id")
    private User userChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User userDeleter;
    private Boolean isDeleted = false;
    private Boolean isEmailConfirmed = false;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Course> courses;
    private String accessRights;
    private String password;

    @NotNull
    private String achievements;

    @NotNull
    private String contactLinks;
    @OneToOne(fetch = FetchType.LAZY)
    private Report report;
    public User(String nickname, String name, String surname, String emailAddress) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
    }
}
