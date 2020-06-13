package Gruppe3.Webseite.service;

import Gruppe3.Webseite.model.Event;

public class data {
    /**
     * Return the currently initialized types of events.
     *
     * @return List of Types
     */
    public String[] getTypes() {
        // TODO
        return new String[]{};
    }

    /**
     * Return the last n events
     *
     * @param n Amount of last events
     * @return List of events
     */
    public Event[] getLastEvents(int n) {
        //TODO
        return new Event[]{};
    }

    /**
     * Returns n Top Events as sorted after total votes
     *
     * @return List of Events
     */
    public Event[] getTopEvents(int n) {
        // TODO
        return new Event[]{};
    }

    /**
     * Search for events with given query string after name
     *
     * @param query String to search for
     * @return List of events
     */
    public Event[] searchForEventAfterName(String query) {
        // TODO
        return new Event[]{};
    }

    /**
     * Search for events with given query string after location
     *
     * @param query String to search for
     * @return List of events
     */
    public Event[] searchForEventAfterLocation(String query) {
        // TODO
        return new Event[]{};
    }

    /**
     * Checks whether an event with given name already exists
     *
     * @return name exists
     */
    public boolean nameTaken() {
        // TODO
        return false;
    }

    /**
     * Save a given new Event to database
     *
     * @param eventToSave Event to add to database
     */
    public void saveEvent(Event eventToSave) {
        // TODO Error throwing here?
    }

    /**
     * Add Like to a given event by name
     *
     * @param name Name of event
     */
    public void addLike(String name) {
        // TODO
    }

    /**
     * Remove Like to a given event by name
     *
     * @param name Name of the event
     */
    public void removeLike(String name) {
        // TODO
    }

    /**
     * Add Dislike to a given event by name
     *
     * @param name Name of event
     */
    public void addDislike(String name) {
        // TODO
    }

    /**
     * Remove Dislike to a given event by name
     *
     * @param name Name of the event
     */
    public void removeDislike(String name) {
        // TODO
    }

    public int getLikeCount() {
        // TODO
        return 0;
    }

    public int getDislikeCount() {
        // TODO
        return 0;
    }
}
