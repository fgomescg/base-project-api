package com.baseproject.api.services;

import com.baseproject.api.model.User;
import com.baseproject.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<User> {

    private final UserRepository repository;

    public List<User> findAll() { return repository.findAll(); }

    public Set<User> findAllByUf(String uf) {
          return new HashSet<>(repository.findAllByUf(uf));
    }

    public User save(User user) { return repository.save(user); }

    public void delete(User user) {
        user.disabled();
        repository.save(user);
    }

    @Override
    public Optional<User> findByCode(UUID code) {
        return repository.findByCode(code);
    }
}
