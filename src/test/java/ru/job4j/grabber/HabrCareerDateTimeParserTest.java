package ru.job4j.grabber;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    void whenParseTrue() {
        HabrCareerDateTimeParser hbr = new HabrCareerDateTimeParser();
        String date = "2023-02-05T10:39:51+03:00";
        LocalDateTime rsl = LocalDateTime.of(2023, 02, 05, 10, 39, 51);
        assertThat(rsl).isEqualTo(hbr.parse(date));
    }

    @Test
    void whenParseFalse() {
        HabrCareerDateTimeParser hbr = new HabrCareerDateTimeParser();
        String date = "2023-07-05T10:39:51+03:00";
        LocalDateTime rsl = LocalDateTime.of(2023, 02, 05, 10, 39, 51);
        assertThat(rsl).isNotEqualTo(hbr.parse(date));
    }
}