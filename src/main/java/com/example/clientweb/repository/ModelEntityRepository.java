package com.example.clientweb.repository;

import com.example.clientweb.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ModelEntityRepository<E extends ModelEntity> extends JpaRepository<E, Integer> {
}
