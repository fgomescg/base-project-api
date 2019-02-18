package com.baseproject.api.repository;

import com.baseproject.api.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findByCode(UUID code);

    List<User> findAllByUf(String uf);

}
