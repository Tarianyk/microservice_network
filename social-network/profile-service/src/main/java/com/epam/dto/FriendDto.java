package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private Date dateOfBirth;
}
