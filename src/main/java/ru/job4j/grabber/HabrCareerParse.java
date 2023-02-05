package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Post;

import java.io.IOException;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK =
            String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 6; i++) {
            String page = String.format("%s?page=%s", PAGE_LINK, i);
            Connection connection = Jsoup.connect(page);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dataElement = row.select(".vacancy-card__date").first();
                Element dataV = dataElement.child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String data = dataV.attr("datetime");
                HabrCareerDateTimeParser hbr = new HabrCareerDateTimeParser();
                String dataFormat = hbr.parse(data).toString();
                System.out.printf("%s, %s %s%n", dataFormat, vacancyName, link);
            });
        }
    }

    private String retrieveDescription(String link) throws IOException {
        final String[] rsl = {""};
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".vacancy-show");
        rows.forEach(row -> rsl[0] = row.select(".vacancy-description__text").text());
        return rsl[0];
    }

    @Override
    public List<Post> list(String link) {
        return null;
    }
}
