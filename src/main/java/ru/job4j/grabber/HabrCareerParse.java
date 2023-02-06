package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK =
            String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        HabrCareerDateTimeParser timeParser = new HabrCareerDateTimeParser();
        HabrCareerParse hbr = new HabrCareerParse(timeParser);
        for (int i = 1; i < 6; i++) {
            String page = String.format("%s?page=%s", PAGE_LINK, i);
            System.out.println(hbr.list(page));
        }
    }

    private String retrieveDescription(String link) {
        try {
            return Jsoup.connect(link).get().select(".vacancy-description__text").text();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        try {
            Connection connection = Jsoup.connect(link);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dataElement = row.select(".vacancy-card__date").first();
                Element dataV = dataElement.child(0);
                String data = dataV.attr("datetime");
                String linkVacancy = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                HabrCareerDateTimeParser hbr = new HabrCareerDateTimeParser();
                rsl.add(new Post(
                        rsl.size(),
                        titleElement.text(),
                        linkVacancy,
                        retrieveDescription(linkVacancy),
                        hbr.parse(data)));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
