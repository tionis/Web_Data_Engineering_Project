package Gruppe3.Webseite.persistence.repository;

import Gruppe3.Webseite.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

}
