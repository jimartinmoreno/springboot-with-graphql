package com.springboot.graphql.api.springbootgraphqlapi.controller;

import com.springboot.graphql.api.springbootgraphqlapi.model.Movie;
import com.springboot.graphql.api.springbootgraphqlapi.repository.MovieRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Slf4j
public class MovieController {

    @Autowired
    private GraphQL graphQL;

    @Autowired
    private MovieRepository movieRepository;

    /**
     * GraphQL endpoint
     * @param query
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> movies(@RequestBody String query) {
        log.info("query: " + query);
        ExecutionResult execute = graphQL.execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    /**
     * Rest endpoint
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Movie>> movies() {
        List<Movie> moviesList = movieRepository.findAll();
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }

}
