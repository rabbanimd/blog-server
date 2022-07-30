package com.cortes.blogger.Data.Models;

import com.cortes.blogger.Data.DTO.CommentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String message;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", post=" + post +
                '}';
    }

    public CommentDTO toCommentDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setName(name);
        commentDTO.setEmail(email);
        commentDTO.setMessage(message);
        return commentDTO;
    }
}
