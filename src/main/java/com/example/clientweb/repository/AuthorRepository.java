package com.example.clientweb.repository;

import com.example.clientweb.data.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends ModelRepository<Author> {

    @Query(value = "select a.id, a.image_id, a.name, a.description, ur1.value as rating, " +
            "count(ur.id) filter ( where ur.value = 1 ) as likes, count(ur.id) filter ( where ur.value = -1 ) as dislikes " +
            "from author a " +
            "left join user_ratings ur on a.id = ur.author_id " +
            "left join user_ratings ur1 on a.id = ur1.author_id and ur1.user_id = ?1 " +
            "group by a.id, a.image_id, a.name, a.description, ur1.value",
            nativeQuery = true)
    Page<Author> findAllAuthor(int userId, Pageable pageable);

    @Query(value = "select a.id, a.image_id, a.name, a.description, ur1.value as rating, " +
            "count(ur.id) filter ( where ur.value = 1 ) as likes, count(ur.id) filter ( where ur.value = -1 ) as dislikes " +
            "from author a " +
            "left join user_ratings ur on a.id = ur.author_id " +
            "left join user_ratings ur1 on a.id = ur1.author_id and ur1.user_id = ?2 " +
            "where a.id = ?1 " +
            "group by a.id, a.image_id, a.name, a.description, ur1.value",
            nativeQuery = true)
    Optional<Author> findByAuthor(int authorId, int userId);
}
