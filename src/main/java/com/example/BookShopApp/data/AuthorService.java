package com.example.BookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<Character, List<Author>> getAuthors() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setAuthorName(rs.getString("authorName"));
            return author;
        });
        Comparator<Author> authorComparator = Comparator.comparing(Author::getAuthorName);
        Collections.sort(authors, authorComparator);

        Map<Character, List<Author>> sortedAuthors = new HashMap<>();
        List<Character> firstLetters = authors.stream().map(c -> c.getAuthorName().toCharArray()[0]).distinct().collect(Collectors.toList());
        for (Character character : firstLetters) {
            List<Author> authorsByLetters = authors.stream()
                    .filter(c -> c.getAuthorName().startsWith(character.toString())).collect(Collectors.toList());
            sortedAuthors.put(character, authorsByLetters);
        }
        return sortedAuthors;
    }
}
