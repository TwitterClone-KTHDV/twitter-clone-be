package com.twitter.twitter.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Twit twit;
}
