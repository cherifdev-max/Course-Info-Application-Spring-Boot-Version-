package com.pluralsight.courseinfo.data;

import org.springframework.data.jpa.repository.JpaRepository;

// C'est tout ! En étendant JpaRepository, Spring nous donne findAll(), findById(), save(), etc.
// Plus besoin d'écrire une seule ligne de SQL.
public interface CourseRepository extends JpaRepository<Course, String> {
}
