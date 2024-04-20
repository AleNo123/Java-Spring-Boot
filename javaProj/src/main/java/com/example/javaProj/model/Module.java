package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateRegistration;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateChanging;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User UserChanger;
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateDeleting;

}
