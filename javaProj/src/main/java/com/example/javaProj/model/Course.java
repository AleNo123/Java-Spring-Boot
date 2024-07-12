package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @NotNull
    @Column(name = "title")
    private String title;
    @Column(name = "description") // Новые поля
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic report;
    @NotBlank
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime timeForCourse;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_creater_id")
    private User userCreater;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_changer_id")
    private User userChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User userDeleter;

    private Boolean isDeleted = false;

    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Module> modules;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> registratedUsers;

    @NotNull
    @NotBlank
    private String tag;
    @NotNull
    @NotBlank
    private String category;
}
