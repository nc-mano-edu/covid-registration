package com.edu.mano.covidregistration.TestAddFiles.repository;

import com.edu.mano.covidregistration.TestAddFiles.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
}
