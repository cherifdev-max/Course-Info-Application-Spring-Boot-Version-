package com.pluralsight.courseinfo.web;

import com.pluralsight.courseinfo.data.Course;
import com.pluralsight.courseinfo.data.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController : Indique que cette classe expose des endpoints REST.
@RestController
@RequestMapping("/courses") // Toutes les URL de cette classe commenceront par /courses
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Mappe les requêtes GET /courses
    @GetMapping
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    // Mappe les requêtes POST /courses/{id}/notes
    @PostMapping("/{id}/notes")
    public Course addNotes(@PathVariable String id, @RequestBody String notes) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        
        Course updatedCourse = new Course(course.getId(), course.getName(), course.getLength(), course.getUrl(), notes);
        return courseRepository.save(updatedCourse);
    }
}
