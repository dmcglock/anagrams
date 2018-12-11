package com.doug.ibotta.words.dao;

import com.doug.ibotta.words.vo.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dictionary extends CrudRepository<Word, Integer> {
}
