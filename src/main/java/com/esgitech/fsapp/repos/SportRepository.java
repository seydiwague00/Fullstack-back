package com.esgitech.fsapp.repos;

import com.esgitech.fsapp.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findByName(String sportName);

    void deleteByName(String name);
}
