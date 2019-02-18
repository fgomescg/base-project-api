package com.baseproject.api.controller;

import com.baseproject.api.converter.UserInConverter;
import com.baseproject.api.converter.UserOutConverter;
import com.baseproject.api.dto.UserDTO;
import com.baseproject.api.exceptions.UserNotFoundException;
import com.baseproject.api.model.User;
import com.baseproject.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserOutConverter outConverter;
    private final UserInConverter inConverter;

    @GetMapping
    public List<UserDTO> list() {
        return service.findAll().stream().map(outConverter).collect(Collectors.toList());
    }

//    @GetMapping{"/{uf}"}
//    public Page<UserDTO> findAllByUf(@PathVariable String uf) {
//        return service.findAllByUf(uf).map(outConverter);
//    }

    @GetMapping("/{code}")
    public UserDTO get(@PathVariable UUID code) {

        // spring-data-rest

        return service.findByCode(code)
                .map(outConverter)
                .orElseThrow(UserNotFoundException::new);

//        Optional<User> optionalUser = service.findByCode(code);
//        User user = optionalUser.orElseThrow(()-> new UserNotFoundException("Usuário nao encontrado"));
//        return outConverter.apply(user);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserDTO dto) {

            User user = service.save(inConverter.convert(dto));

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{code}")
                    .build(user.getCode());

            return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity update(@PathVariable UUID code,
                                 @RequestBody UserDTO dto) {
        User userDatabase = service.findByCode(code).orElseThrow(UserNotFoundException::new);
        User userConverted = inConverter.convert(dto);

        userDatabase.setName(userConverted.getName());
        userDatabase.setUsername(userConverted.getUsername());
        userDatabase.setEmail(userConverted.getEmail());
        userDatabase.setPassword(userConverted.getPassword());

        service.save(userDatabase);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable UUID code) {
        User user = service.findByCode(code).orElseThrow(UserNotFoundException::new);
        service.delete(user);

        return ResponseEntity.noContent().build();
    }

}