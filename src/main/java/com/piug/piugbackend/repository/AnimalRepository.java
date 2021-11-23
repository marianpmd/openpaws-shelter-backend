package com.piug.piugbackend.repository;

import com.piug.piugbackend.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface AnimalRepository extends JpaRepository<AnimalEntity,Long> {
}
