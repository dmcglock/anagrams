package com.doug.ibotta.words.dao;

import com.doug.ibotta.words.vo.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Dictionary extends CrudRepository<Word, Integer> {

    List<Word> findByWordAlphabeticalAndWordNotIn(String wordAlphabetical, String word);

    @Transactional
    Long deleteByWord(String word);

    @Transactional
    Long deleteByWordAlphabetical(String wordAlphabetical);
}
