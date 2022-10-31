package com.example.clientweb.repository.educationRepository;

import com.example.clientweb.model.education.Education;
import com.example.clientweb.repository.ModelEntityRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EducationRepository<E extends Education> extends ModelEntityRepository<E> {

}
