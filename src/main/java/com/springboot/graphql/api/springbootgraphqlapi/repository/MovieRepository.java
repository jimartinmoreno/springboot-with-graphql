package com.springboot.graphql.api.springbootgraphqlapi.repository;

import com.springboot.graphql.api.springbootgraphqlapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
