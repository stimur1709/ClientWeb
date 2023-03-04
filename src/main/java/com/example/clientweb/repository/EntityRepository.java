package com.example.clientweb.repository;

import com.example.clientweb.data.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<E extends Model> extends JpaRepository<E, Integer> {
}
