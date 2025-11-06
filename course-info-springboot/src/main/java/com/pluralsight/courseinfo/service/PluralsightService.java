package com.pluralsight.courseinfo.service;

import com.pluralsight.courseinfo.service.dto.PluralsightCourse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

// @Service : Indique que c'est un composant de logique métier.
@Service
public class PluralsightService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${pluralsight.api.base-url}")
    private String pluralsightBaseUrl;

    public List<PluralsightCourse> getCoursesFor(String authorId) {
        String url = pluralsightBaseUrl + "/profile/data/author/" + authorId + "/all-content";
        PluralsightCourse[] courses = restTemplate.getForObject(url, PluralsightCourse[].class);
        return courses != null ? List.of(courses) : List.of();
    }
}
