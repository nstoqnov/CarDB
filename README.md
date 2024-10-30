# Spring Boot Exercise Project

This project is designed to help you gain hands-on experience with Spring Boot by working on basic CRUD operations with a `Trip` and `User` entity.

## Overview
The project provides a RESTful API to manage trips, allowing you to create, view, and delete trips stored in the database.

## API Endpoints

Here are the available endpoints:
### Trips
- **GET** `/trips`  
  Returns all trips in the database.

- **POST** `/trips`  
  Creates a new trip.

- **GET** `/trips/{id}`  
  Retrieves a specific trip by its ID.

- **DELETE** `/trips/{id}`  
  Deletes a trip by its ID.

---
### Users
_Note: All requests below requires admin rights._
- **GET** `/users`  
  Returns all users in the database.

- **POST** `/users`  
  Creates a new user.

- **GET** `/users/{id}`  
  Retrieves a specific user by its ID.

- **DELETE** `/users/{id}`  
  Deletes a user by its ID.
  
Each endpoint follows standard HTTP methods and returns JSON-formatted responses.
