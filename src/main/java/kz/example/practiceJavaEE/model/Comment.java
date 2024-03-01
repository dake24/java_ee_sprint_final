package kz.example.practiceJavaEE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String comment;
    private Timestamp postDate;
    private int userId;
    private int newsId;
}
