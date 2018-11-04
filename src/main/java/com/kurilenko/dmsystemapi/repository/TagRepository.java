package com.kurilenko.dmsystemapi.repository;

import com.kurilenko.dmsystemapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Transactional
    Optional<Tag> findByName(String name);
}
