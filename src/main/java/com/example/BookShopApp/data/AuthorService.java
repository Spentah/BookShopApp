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

//    public Map<Character, List<Author>> getAuthors() {
//        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) -> {
//            Author author = new Author();
//            author.setFirstName(rs.getString("first_name"));
//            return author;
//        });
//        Comparator<Author> authorComparator = Comparator.comparing(Author::getFirstName);
//        Collections.sort(authors, authorComparator);
//
//        Map<Character, List<Author>> sortedAuthors = new HashMap<>();
//        List<Character> firstLetters = authors.stream().map(c -> c.getFirstName().toCharArray()[0]).distinct().collect(Collectors.toList());
//        for (Character character : firstLetters) {
//            List<Author> authorsByLetters = authors.stream()
//                    .filter(c -> c.getFirstName().startsWith(character.toString())).collect(Collectors.toList());
//
//            sortedAuthors.put(character, authorsByLetters);
//        }
//        return sortedAuthors;
//    }

    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            return author;
        });
        return authors.stream().collect(Collectors.groupingBy((Author a) -> a.getLastName().substring(0, 1)));
    }
}
