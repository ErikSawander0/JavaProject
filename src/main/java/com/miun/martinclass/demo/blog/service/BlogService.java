package com.miun.martinclass.demo.blog.service;

import com.miun.martinclass.demo.blog.entity.BlogEntry;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BlogService {

    @PersistenceContext
    private EntityManager em;

    // Save a blog entry (insert or update)
    public Long saveBlogEntry(BlogEntry blogEntry) {
        if (blogEntry.getId() == null) {
            em.persist(blogEntry);   // new entry
        } else {
            blogEntry = em.merge(blogEntry); // update existing
        }
        return blogEntry.getId();
    }

    // Get all blog entries (ordered by date descending)
    public List<BlogEntry> getAllBlogEntries() {
        return em.createQuery("SELECT b FROM BlogEntry b ORDER BY b.date DESC", BlogEntry.class)
                .getResultList();
    }

    // Get a blog entry by ID
    public BlogEntry getBlogEntryById(Long id) {
        return em.find(BlogEntry.class, id);
    }

    // Delete a blog entry by ID
    public void deleteBlogEntryById(Long id) {
        BlogEntry entry = em.find(BlogEntry.class, id);
        if (entry != null) {
            em.remove(entry);
        }
    }
}
