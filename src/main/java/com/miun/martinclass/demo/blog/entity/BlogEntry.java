package com.miun.martinclass.demo.blog.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BlogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String entry;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] picture;//Note that this is the image, the blob we spoke of

    @Column(nullable = false)
    private LocalDateTime date;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public byte[] getPicture() {
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
        this.date = LocalDateTime.now();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEntry(String entry) {
        this.entry = entry;
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
