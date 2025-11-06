package com.pluralsight.courseinfo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Duration;
import java.time.LocalTime;

// DTO (Data Transfer Object) qui représente les données de l'API Pluralsight.
@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(String id, String title, String duration, String contentUrl, boolean isRetired) {

    /**
     * Convertit la durée textuelle (ex: "01:30:55") en un nombre total de minutes.
     * Cette méthode est robuste et gère différents formats de temps.
     */
    public long durationInMinutes() {
        // On utilise LocalTime.parse, qui est conçu pour comprendre les formats de temps comme HH:mm:ss.SSS
        // Puis on calcule la durée entre le début de la journée (minuit) et ce temps.
        return Duration.between(LocalTime.MIN, LocalTime.parse(duration)).toMinutes();
    }
}