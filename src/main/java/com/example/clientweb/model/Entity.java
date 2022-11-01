package com.example.clientweb.model;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
