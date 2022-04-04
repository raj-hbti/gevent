package com.hackerrank.gevents.repository;

import java.util.List;

import com.hackerrank.gevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.repoId = :repoId")
    List<Event> findAllByRepoId(@Param("repoId") Integer repoId);

}
