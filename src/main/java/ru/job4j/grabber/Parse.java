package ru.job4j.grabber;

import ru.job4j.Post;

import java.util.List;

public interface Parse {

    List<Post> list(String link);

}
