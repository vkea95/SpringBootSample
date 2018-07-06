package com.vkea.sbs.repository;

import com.vkea.sbs.model.Program;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProgramRepository extends ElasticsearchRepository<Program, String> {
    List<Program> findByTitle(String title);

}
