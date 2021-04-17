package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.ServiceRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegDto {

    @NotNull
    @Size(min=2,max=15)
    private String name;
    @NotNull
    @Size(min=2,max=15)
    private String surname;
    @NotNull
    @Size(min=5)
    private String login;
    @NotNull
    @Size(min=6)
    private String password;

}