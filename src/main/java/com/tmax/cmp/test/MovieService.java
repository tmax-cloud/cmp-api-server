package com.tmax.cmp.test;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    public List<Movie> query(final String query) {

        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test123").build(),
                Movie.builder().title("영화2").link("http://test345").build()
        );
    }
}
