package com.pluralsight.courseinfo.service;

import com.pluralsight.courseinfo.data.Course;
import com.pluralsight.courseinfo.data.CourseRepository;
import com.pluralsight.courseinfo.service.dto.PluralsightCourse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseStorageService {

    private final CourseRepository courseRepository;

    // Le CourseRepository est automatiquement "injecté" par Spring.
    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    } 

    // @Transactional : Garantit que soit tous les cours sont sauvegardés, soit aucun.
    @Transactional
    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {
        String baseUrl = "https://app.pluralsight.com";
        for (PluralsightCourse psCourse : psCourses) {
            Course course = new Course(psCourse.id(),
                                     psCourse.title(),
                                     psCourse.durationInMinutes(),
                                     baseUrl + psCourse.contentUrl(),
                                     null); // Notes initiales
            courseRepository.save(course);
        }
    }
}
