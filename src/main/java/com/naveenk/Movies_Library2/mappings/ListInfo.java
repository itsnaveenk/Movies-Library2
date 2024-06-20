package com.naveenk.Movies_Library2.mappings;

public class ListInfo {
    private Long id;
    private String name;

    public ListInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}