package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    private Boolean isDeleted = false;
    @CreationTimestamp
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;
    @UpdateTimestamp
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_creator_id")
    private User userCreater;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_changer_id")
    private User userChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User userDeleter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    @OneToOne(fetch = FetchType.LAZY)
    private Mark mark;
}
