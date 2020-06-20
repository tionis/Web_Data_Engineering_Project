package Gruppe3.Webseite.persistence.repository;

import Gruppe3.Webseite.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query(value = "SELECT * FROM event ORDER BY likes-dislikes DESC LIMIT :count", nativeQuery = true)
    Event[] getTopEvents(@Param("count") Integer count);

    @Query(value = "SELECT * FROM event ORDER BY creation_date DESC LIMIT :count", nativeQuery = true)
    Event[] getLastEvents(@Param("count") Integer count);
}
