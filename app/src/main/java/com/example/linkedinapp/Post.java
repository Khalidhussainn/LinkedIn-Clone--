// Post.java
package com.example.linkedinapp;

public class Post {
    private String author;
    private String content;
    private int likes;
    private int shares;
    private int comments;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String author, String content, int likes, int shares, int comments) {
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.shares = shares;
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getId() {
        return author + content; // or generate a unique ID
    }
}
