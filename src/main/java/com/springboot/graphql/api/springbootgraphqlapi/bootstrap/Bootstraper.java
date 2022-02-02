package com.springboot.graphql.api.springbootgraphqlapi.bootstrap;

import com.springboot.graphql.api.springbootgraphqlapi.model.Movie;
import com.springboot.graphql.api.springbootgraphqlapi.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class Bootstraper implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        loadDataIntoHSQL();
    }

    private void loadDataIntoHSQL() {
        log.info("Loading Data Into DB...");
        Stream.of(Movie.builder().id("1001").title("Guason")
                        .actors(new String[]{"Joaquin Phoenix", "Robert De Niro"})
                        .directors(new String[]{"Todd Phillips"}).releaseDate("3 de octubre de 2019")
                        .build(),
                Movie.builder().id("1002").title("Avengers: Endgame")
                        .actors(new String[]{"Robert Downey Jr.", "Scarlett Johansson", "Chris Evans"})
                        .directors(new String[]{"Anthony Russo", "Joe Russo"}).releaseDate("22 de abril de 2019")
                        .build()
        ).forEach(movie -> movieRepository.save(movie));

        log.info("Data loaded!!");
    }
}