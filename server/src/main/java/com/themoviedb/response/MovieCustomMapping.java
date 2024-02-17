package com.themoviedb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface MovieCustomMapping {
    Long getId();

    String getTitle();

    @JsonProperty("poster_path")
    String getPosterPath();
}
