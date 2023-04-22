package com.example.clientweb.repository;

import com.example.clientweb.data.model.UserRatings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public interface UserRatingsRepository extends ModelRepository<UserRatings> {

    @Async
    @Transactional
    @Modifying
    @Query(value = "insert into user_ratings(user_id, author_id, value) values (?1, ?2, ?3) " +
            "on conflict(user_id, author_id) do update set user_id = ?1, author_id = ?2, value = ?3",
            nativeQuery = true)
    void saveAuthorRating(int userId, int authorId, int value);

    @Async
    @Transactional
    @Modifying
    @Query(value = "insert into user_ratings(user_id, item_id, value) values (?1, ?2, ?3) " +
            "on conflict(user_id, item_id) do update set user_id = ?1, item_id = ?2, value = ?3",
            nativeQuery = true)
    void saveItemRating(int userId, int itemId, int value);

}
