package com.example.clientweb.repository;

import com.example.clientweb.data.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends ModelRepository<Item> {

    @Query(value = "select i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, i.duration, " +
            "i.description, i.type_id, ur1.value as rating, " +
            "count(ur.id) filter ( where ur.value = 1 ) as likes, count(ur.id) filter ( where ur.value = -1 ) as dislikes " +
            "from item i " +
            "left join user_ratings ur on i.id = ur.author_id " +
            "left join user_ratings ur1 on i.id = ur1.author_id and ur1.user_id = ?2 " +
            "where i.type_id = ?1 " +
            "group by i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, " +
            "i.duration, i.description, i.type_id, ur1.value",
            nativeQuery = true)
    Page<Item> findItemsByTypeId(int typeId, int userId, Pageable pageable);

    @Query(value = "select i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, i.duration, " +
            "i.description, i.type_id, ur1.value as rating, " +
            "count(ur.id) filter ( where ur.value = 1 ) as likes, count(ur.id) filter ( where ur.value = -1 ) as dislikes " +
            "from item i " +
            "left join user_ratings ur on i.id = ur.author_id " +
            "left join user_ratings ur1 on i.id = ur1.author_id and ur1.user_id = ?1 " +
            "group by i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, " +
            "i.duration, i.description, i.type_id, ur1.value",
            nativeQuery = true)
    Page<Item> findItems(Integer userId, Pageable pageable);


    @Query(value = "select i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, i.duration, " +
            "i.description, i.type_id, ur1.value as rating, " +
            "count(ur.id) filter ( where ur.value = 1 ) as likes, count(ur.id) filter ( where ur.value = -1 ) as dislikes " +
            "from item i " +
            "left join user_ratings ur on i.id = ur.author_id " +
            "left join user_ratings ur1 on i.id = ur1.author_id and ur1.user_id = ?2 " +
            "where i.id = ?1 " +
            "group by i.id, i.image_id, i.title, i.popularity, i.rate, i.popularity, i.created_date, " +
            "i.duration, i.description, i.type_id, ur1.value",
            nativeQuery = true)
    Optional<Item> findByItem(int itemId, Integer userId);
}
