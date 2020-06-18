#!/bin/bash
curl --header "Content-Type: application/json" --data '{"name":"test","type":"default","location":"Passau","startDate":"2020-06-18T10:14:24.386+00:00","description":"Nope"}' http://localhost:8090/api/create
curl --header "Content-Type: application/json" --data '{"name":"test2","type":"default","location":"Munich","startDate":"2020-06-18T10:14:24.386+00:00","description":"Maybe Here?"}' http://localhost:8090/api/create
curl --header "Content-Type: application/json" --data '{"name":"test3","type":"default","location":"Everywhere","startDate":"2020-06-18T10:14:24.386+00:00","description":"Still no description!"}' http://localhost:8090/api/create
curl --header "Content-Type: application/json" --data '{"name":"NotATest","type":"default","location":"Passau","startDate":"2020-06-18T10:14:24.386+00:00","description":"Nope, not doing that!"}' http://localhost:8090/api/create
curl --header "Content-Type: application/json" --data '{"name":"MaybeThisIsATest","type":"default","location":"Passau", "description":"Well.... No!"}' http://localhost:8090/api/create
