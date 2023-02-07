package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK =
            String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final int PAGE = 5;

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        DateTimeParser timeParser = new HabrCareerDateTimeParser();
        HabrCareerParse hbr = new HabrCareerParse(timeParser);
        System.out.println(hbr.list(PAGE_LINK));
    }

    private String retrieveDescription(String link) {
        try {
            return Jsoup.connect(link).get().select(".vacancy-description__text").text();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private Post parse(Element element) {
            Element titleElement = element.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            Element dataElement = element.select(".vacancy-card__date").first();
            Element dateV = dataElement.child(0);
            String linkVacancy = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        return new Post(
                titleElement.text(),
                linkVacancy,
                retrieveDescription(linkVacancy),
                dateTimeParser.parse(dateV.attr("datetime")));
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        try {
            for (int i = 1; i <= PAGE; i++) {
                String page = String.format("%s?page=%s", link, i);
                Connection connection = Jsoup.connect(page);
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> rsl.add(parse(row)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
