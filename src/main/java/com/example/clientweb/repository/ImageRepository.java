package com.example.clientweb.repository;

import com.example.clientweb.data.model.Image;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ImageRepository extends ModelRepository<Image> {

    @Query(value = "select * from image im " +
            "    left join item i on im.id = i.image_id " +
            "    left join author a on im.id = a.image_id " +
            "where i.image_id is null and a.image_id is null ", nativeQuery = true)
    List<Image> findUnusedPictures();

    @Query("select i from Image i where i.name in ?1")
    List<Image> findByNameIn(Collection<String> names);
}
