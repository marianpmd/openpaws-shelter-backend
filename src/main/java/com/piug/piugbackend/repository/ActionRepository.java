package com.piug.piugbackend.repository;


import com.piug.piugbackend.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ActionRepository extends JpaRepository<ActionEntity,Long> {
}
