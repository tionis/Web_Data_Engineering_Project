# API Documentation
## Data Types
### Event Object
An event object consists of following values (java like types included for understanding of valid values):
 - String name
 - String location - only set this string if you dont want to save coordinates
 - Double longitude
 - Double latitude
 - Date startDate
 - Date creationDate
 - String name
 - String descriptions
 - int likes
 - int dislikes

### Vote Object
This Object represents a vote operation and consists of these values:
 - String eventName - The name of the event the vote is valid for
 - Boolean isLike - Specifies whether this vote is a like

## Endpoints
### GET Resources
#### /api/events?n=YOUR_VALUE_HERE
 Returns the last n events as an array with Event Objects.

### /api/event/EVENT_NAME_HERE
Returns an event of this specific name, if it does not exist HTTP 404 is returned.

### /api/search/location?q=YOUR_VALUE_HERE
Returns an array of events found for this search query after location.

### /api/search/name?q=YOUR_VALUE_HERE
Returns an array of events found for this search query after name.

### /api/types
Returns the currently active event types.

### /api/top?n=YOUR_VALUE_HERE
Returns the top n events as array.

### POST Resources
#### /api/vote/add
Accepts a vote object to add to its event.

#### /api/vote/remove
Accepts a vote object to remove from its event.

### /api/create
Accepts an event to save to data store. Returns HTTP 409 if it already exists.  
Returns the event as it was processed by the server.