package Gruppe3.Webseite.service;

import Gruppe3.Webseite.model.Event;
import Gruppe3.Webseite.model.Vote;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Data {
    // Public Methods
    
    /**
     * Return the currently initialized types of events.
     *
     * @return List of Types
     */
    public String[] getTypes() {
        // TODO
        // get array from initialization(maybe save in database?)
        return new String[]{};
    }

    /**
     * Return the last n events.
     *
     * @param n Amount of last events
     * @return List of events
     */
    public Event[] getLastEvents(final int n) {
        // TODO
        // Only future events
        // SELECT * FROM events ORDER BY creation_date DESC LIMIT n;
        return new Event[]{};
    }

    /**
     * Returns n Top Events as sorted after total votes.
     *
     * @param n Amount of events to return
     * @return List of Events
     */
    public Event[] getTopEvents(final int n) {
        // TODO
        // SQL query that sorts by vote and takes the top n ones:
        // SELECT * FROM events ORDER BY (likes-dislikes) DESC LIMIT n;
        return new Event[]{};
    }

    /**
     * Search for events with given query string after name.
     *
     * @param query String to search for
     * @return List of events
     */
    public Event[] searchForEventAfterName(final String query) {
        // TODO
        // sql search?
        return new Event[]{};
    }

    /**
     * Search for events with given query string after location.
     *
     * @param query String to search for
     * @return List of events
     */
    public Event[] searchForEventAfterLocation(final String query) {
        // TODO
        // sql search?
        return new Event[]{};
    }

    /**
     * Save a given new Event to database.
     *
     * @param eventToSave Event to add to database
     */
    public void saveEvent(final Event eventToSave) {
        // sql query to add to table
    }

    /**
     * Apply Vote Addition to data store.
     *
     * @param vote Vote to add
     */
    public void addVote(final Vote vote) throws NoSuchEvent {
        //TODO
        if (vote.getIsLike()) {
            // Update event in database
        } else {
            // Update event in database
        }
    }

    /**
     * Apply Vote Removal to data store.
     *
     * @param vote Vote to remove
     */
    public void removeVote(final Vote vote) throws NoSuchEvent {
        //TODO
        if (vote.getIsLike()) {
            // Update event in database
        } else {
            // Updare event in database
        }
    }

    /**
     * Get Amount of likes for a given event by name.
     *
     * @param name Name of Event
     * @return Amount of likes
     */
    public int getLikeCount(final String name) throws NoSuchEvent {
        // Is this efficient enough?
        return getEventByName(name).getLikes();
    }

    /**
     * Get Amount of dislikes for a given event by name.
     *
     * @param name Name of event
     * @return Amount of Dislikes
     */
    public int getDislikeCount(final String name) throws NoSuchEvent {
        // Is this efficient enough?
        return getEventByName(name).getDislikes();
    }

    /**
     * Get an event by specifying its name.
     *
     * @param name Name of the event
     * @return Event with given name
     * @throws NoSuchEvent No event with given name found
     */
    public Event getEventByName(final String name) throws NoSuchEvent {
        if (name != null && !name.isEmpty()) {
            // TODO
            // SQl query for specific name:
            // SELECT * FROM events WHERE name = 'test'
            return new Event(name, "", new Date(), "", "");
        } else {
            throw new NoSuchEvent("Empty Name");
        }
    }
}
