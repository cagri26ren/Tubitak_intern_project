package yte.intern.project.application.usecases.ManageEvents.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
