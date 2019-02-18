package com.baseproject.api.services;

import java.util.Optional;
import java.util.UUID;

abstract class AbstractService<T> {

    protected abstract Optional<T> findByCode(UUID code);

}
