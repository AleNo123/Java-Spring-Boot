package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "topic_seq", sequenceName = "topic_seq", allocationSize = 1)
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
    private Long topicId;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Module module;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
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
    @JoinColumn(name="user_creator_id")
    private User userCreater;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_changer_id")
    private User userChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_deleter_id")
    private User userDeleter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;

    @NotNull
    @NotBlank
    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> task;
}
