package com.springboot.graphql.api.springbootgraphqlapi.graphql.fetcher;

import com.springboot.graphql.api.springbootgraphqlapi.model.Movie;
import com.springboot.graphql.api.springbootgraphqlapi.repository.MovieRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyMoviesDataFetcher {

    @Autowired
    private MovieRepository movieRepository;

    public DataFetcher<List<Movie>> getMyMoviesDataFetcher() {
        return dataFetchingEnvironment -> movieRepository.findAll();
    }
}
