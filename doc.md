# Documentation for my project

## API Endpoints

| Method | URL                     | Description                                 |
|--------|-------------------------|---------------------------------------------|
| POST   | `/api/auth/register`    | Registration, returns JWT                   |
| POST   | `/api/auth/login`       | Login, returns JWT                          |
| GET    | `/api/users/{id}`       | Get profile by id, only for admins          |
| PUT    | `/api/users/{id}`       | Update profile by id, only for admins       |
| POST   | `/api/posts`            | Create post                                 |
| GET    | `/api/posts`            | List of posts                               |
| GET    | `/api/posts/{id}`       | Get post by id                              |
| PUT    | `/api/posts/{id}`       | Update post                                 |
| DELETE | `/api/posts/{id}`       | Delete post                                 |
| POST   | `/api/friends/{id}`     | Send request                                |
| DELETE | `/api/friends/{id}`     | Delete from friends                         |
| GET    | `/api/friends`          | List of friens                              | 
