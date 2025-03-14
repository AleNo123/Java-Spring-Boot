package com.example.javaProj.DTO;

import com.example.javaProj.View.CourseNameView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull
    @Size(min = 3,max = 30)
    private String name;
    @NotNull
    @Size(min = 3,max = 30)
    private String surname;

    private MultipartFile file;

    private String contactLinks;
}
