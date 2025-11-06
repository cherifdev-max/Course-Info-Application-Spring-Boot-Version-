package com.pluralsight.courseinfo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

// @Entity : Indique que cette classe est une table de base de données.
@Entity
@Table(name = "COURSES")
public final class Course { // On la déclare 'final' par bonne pratique, mais pas 'record'

    @Id // @Id : Indique que ce champ est la clé primaire.
    private String id;

    private String name;
    private long length;
    private String url;
    private String notes;

    // Constructeur sans argument, OBLIGATOIRE pour JPA/Hibernate.
    public Course() {
    }

    // Un constructeur pratique pour nous.
    public Course(String id, String name, long length, String url, String notes) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.url = url;
        this.notes = notes;
    }

    // --- Getters et Setters, OBLIGATOIRES pour que JPA/Hibernate puisse fonctionner ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // --- equals, hashCode et toString, bonnes pratiques pour les entités ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
