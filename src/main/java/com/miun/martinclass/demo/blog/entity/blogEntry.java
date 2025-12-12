package com.miun.martinclass.demo.blog.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class blogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;
    private String entry;
    private String picture;//Note that this is the image, the blob we spoke of
    LocalDateTime date;

    public String getName() {
        return name;
    }
    public LocalDateTime getdate() {
        return date;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getpicture() {
        return picture;
    }
    public String getEntry() {
        return entry;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEntry(String entry) {
        this.entry = entry;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
