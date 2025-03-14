package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "module")
@SequenceGenerator(name = "module_seq", sequenceName = "module_seq", allocationSize = 1)
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String content;
    private Boolean isDeleted = false;
    @NotNull
    @Column(name = "description") // Новые поля
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;
    @UpdateTimestamp
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
    private Course course;
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private List<Topic> topic;
}
