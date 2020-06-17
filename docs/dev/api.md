# API Documentation
## Data Types
### Event Object
An event object consists of following values (java like types included for understanding of valid values):
 - String name
 - String location (Can be anything, but normally contains city name or coordinates in ISO 6709 format)
 - Date statDate
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

#### /api/events?name=YOUR_VALUE_HERE
Returns an event if one is found for given name, if not HTTP 400 is returned.

### POST Resources
#### /api/vote/add
Accepts a vote object to add to its event.

#### /api/vote/remove
Accepts a vote object to remove from its event.