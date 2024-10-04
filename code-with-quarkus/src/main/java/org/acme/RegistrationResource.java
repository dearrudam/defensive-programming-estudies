package org.acme;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/registration")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class RegistrationResource {

    @POST
    public void register(User user) {
        System.out.println("registering user %s with email %s and age %d"
                .formatted(user.email(), user.data().name(), user.data().age()));
    }

    public static record User(
            @NotNull
            @Email
            String email,
            @NotNull
            @ShouldBeAdultAndNameStartWith("M")
            UserData data) {
    }

    public static record UserData(
            @NotBlank
            String name,
            @Positive
            Integer age) {
    }
}
