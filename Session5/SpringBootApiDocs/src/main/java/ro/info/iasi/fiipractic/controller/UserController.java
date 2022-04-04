package ro.info.iasi.fiipractic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @PatchMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void patchUser(@PathVariable Integer id, @RequestBody Map<String, String> partialUser) {
        userService.patchUser(id, partialUser);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }


}
