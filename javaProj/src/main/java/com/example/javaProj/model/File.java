package com.example.javaProj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "file-with-content",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "content",
                                subgraph = "content-name"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "course-name",
                                attributeNodes = {
                                        @NamedAttributeNode("id")
                                }
                        )
                }
        ),
        @NamedEntityGraph(name = "file")
})
@Entity
@Table(name = "File")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @NotNull
    @NotBlank
    @Column(name = "file_path")
    private String filePath;
    @NotNull
    @NotBlank
    @Column(name = "file_name")
    private String fileName;
    @NotNull
    @NotBlank
    @Column(name = "original_file_name")
    private String originalFileName;
    @NotNull
    @NotBlank
    @Column(name = "file_type")
    private String fileType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime dateCreation;


    public File(@NotNull String filePath, @NotNull String fileName, @NotNull String originalFileName, @NotNull String fileType) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
    }
}
