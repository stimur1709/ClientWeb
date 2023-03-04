package com.example.clientweb.repository;

import com.example.clientweb.data.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends EntityRepository<Item> {
    Page<Item> findByTypeId(int typeId, Pageable pageable);
}
