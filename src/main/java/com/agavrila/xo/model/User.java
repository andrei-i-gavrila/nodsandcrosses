package com.agavrila.xo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Boolean playing;

    public User() {
    }


    public User(Long id, String username, String password, Boolean playing) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.playing = playing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPlaying() {
        return playing;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }
}
