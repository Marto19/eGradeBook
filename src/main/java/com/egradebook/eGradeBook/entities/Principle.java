package com.egradebook.eGradeBook.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "principles")
public class Principle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User userId;

    public Principle(long id, User userId) {
        this.id = id;
        this.userId = userId;
    }

    public Principle() {

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
