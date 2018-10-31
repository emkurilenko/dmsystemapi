package com.kurilenko.dmsystemapi.repository;

import com.kurilenko.dmsystemapi.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
