package com.example.demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RestController
public class RegistrationController {

    @PostMapping("api/registration")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void register(@RequestBody @Valid User user) {

        System.out.println("registering user %s with email %s"
                .formatted(user.email(), user.data().name()));
    }

    record User(@Email
                String email,
                @NotNull @DeveSerMaiorDeIdadeENomeComecaComA
                UserData data) { }

    record UserData(@NotBlank String name, @Positive Integer age) {

    }
}
