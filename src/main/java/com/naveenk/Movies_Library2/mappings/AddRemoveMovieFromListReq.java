package com.naveenk.Movies_Library2.mappings;

import lombok.Data;

@Data
public class AddRemoveMovieFromListReq {
    private String listName;
    private String movieID;
}
