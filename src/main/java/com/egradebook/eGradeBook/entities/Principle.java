package com.egradebook.eGradeBook.entities;

public class Principle {
    private long id;
    private User userId;

    public Principle(long id, User userId) {
        this.id = id;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Principle{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
