package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.service.CourseStorageService;
import com.pluralsight.courseinfo.service.PluralsightService;
import com.pluralsight.courseinfo.service.dto.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseCliRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CourseCliRunner.class);

    private final PluralsightService pluralsightService;
    private final CourseStorageService courseStorageService;

    public CourseCliRunner(PluralsightService pluralsightService, CourseStorageService courseStorageService) {
        this.pluralsightService = pluralsightService;
        this.courseStorageService = courseStorageService;
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            LOG.warn("Please provide an author name as an argument.");
            return;
        }

        try {
            String authorId = args[0];
            LOG.info("Retrieving courses for author '{}'", authorId);
            List<PluralsightCourse> coursesToStore = pluralsightService.getCoursesFor(authorId)
                    .stream()
                    .filter(c -> !c.isRetired())
                    .toList();

            LOG.info("Found {} courses. Storing them in the database.", coursesToStore.size());
            courseStorageService.storePluralsightCourses(coursesToStore);
            LOG.info("Courses stored successfully.");

        } catch (Exception e) {
            LOG.error("An unexpected error occurred.", e);
        }
    }
}
