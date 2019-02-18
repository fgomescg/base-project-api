package com.baseproject.api.repository.search;

import com.baseproject.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@FunctionalInterface
public interface UserSearchRepository {

    Page<User> search(Filter filter, Pageable pageable);

    interface Filter {

        String getUsername();
    }
}
