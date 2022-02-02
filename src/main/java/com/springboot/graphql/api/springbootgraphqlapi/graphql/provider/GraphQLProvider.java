package com.springboot.graphql.api.springbootgraphqlapi.graphql.provider;

import com.springboot.graphql.api.springbootgraphqlapi.graphql.fetcher.MyMoviesDataFetcher;
import com.springboot.graphql.api.springbootgraphqlapi.model.Movie;
import com.springboot.graphql.api.springbootgraphqlapi.repository.MovieRepository;
import com.springboot.graphql.api.springbootgraphqlapi.graphql.fetcher.AllMoviesDataFetcher;
import com.springboot.graphql.api.springbootgraphqlapi.graphql.fetcher.MovieDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class GraphQLProvider {

    @Autowired
    private AllMoviesDataFetcher allMoviesDataFetcher;

    @Autowired
    private MovieDataFetcher movieDataFetcher;

    @Autowired
    private MyMoviesDataFetcher myMoviesDataFetcher;

    @Value("classpath:schema.graphql")
    private Resource resource;

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allMovies", allMoviesDataFetcher)
                        .dataFetcher("movie", movieDataFetcher)
                        // NACHO el fetcher se puede crear con una funcion lambda
                        .dataFetcher("myMovies", myMoviesDataFetcher.getMyMoviesDataFetcher()))
                .build();
    }

    @Bean
    public GraphQL graphQL() throws IOException {
        File file = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
