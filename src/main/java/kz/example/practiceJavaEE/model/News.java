package kz.example.practiceJavaEE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer id;
    private Timestamp postDate;
    private NewsCategory category;
    private String title;
    private String content;
}
