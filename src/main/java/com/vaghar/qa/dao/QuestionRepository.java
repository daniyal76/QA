package com.vaghar.qa.dao;

import com.vaghar.qa.model.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, String> {

    QuestionEntity findByTitle(String title);
}
