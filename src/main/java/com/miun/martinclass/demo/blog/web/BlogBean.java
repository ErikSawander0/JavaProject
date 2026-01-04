package com.miun.martinclass.demo.blog.web;

import com.miun.martinclass.demo.blog.entity.BlogEntry;
import com.miun.martinclass.demo.blog.service.BlogService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import java.util.ArrayList;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class BlogBean {

    @EJB
    private BlogService blogService;

    private BlogEntry blogEntry = new BlogEntry();
    private Long blogId;
    private Part imageFile;

    // Handle uploaded image
    public Part getImageFile() {
        return imageFile;
    }

    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }

    // Fetch all blog entries
    public List<BlogEntry> getAllBlogEntries() {
        return blogService.getAllBlogEntries();
    }

    // Save a blog entry
    public String saveBlogEntry() {
        try {
            if (imageFile != null) {
                byte[] imageBytes = imageFile.getInputStream().readAllBytes();
                blogEntry.setPicture(imageBytes); // entity field is 'picture'
            }
            blogService.saveBlogEntry(blogEntry);
            return "blogList.xhtml?faces-redirect=true"; // navigate back to list
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load a blog entry by ID
    public void loadBlogEntry() {
        if (blogId != null) {
            blogEntry = blogService.getBlogEntryById(blogId);
        }
    }

    // Delete a blog entry by ID
    public void deleteBlogEntry(Long id) {
        blogService.deleteBlogEntryById(id);
    }

    // Getters/Setters
    public BlogEntry getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(BlogEntry blogEntry) {
        this.blogEntry = blogEntry;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getPictureAsBase64(byte[] picture) {
        if (picture == null || picture.length == 0) {
            return ""; // optionally return a placeholder image
        }
        return Base64.getEncoder().encodeToString(picture);
    }

}