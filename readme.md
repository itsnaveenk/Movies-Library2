# Movies-Library2

Movies-Library2 is a Spring Boot application that provides a movie library service. It allows users to create and manage their own movie lists.

## Features

- User registration and login with secure authentication
- Role-based authorization for different user types
- Create and fetch movie lists
- Search for movies
- Add and remove movies from lists

## Technologies Used

- Java
- Spring Boot
- Spring Security for authentication and authorization
- Spring Data JPA for database operations
- MySQL for data storage
- JSON Web Tokens (JWT) for secure session management
- Lombok for reducing boilerplate code
- Maven for project management

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- MySQL
- Maven

### Installation

1. Clone the repository
```bash
git clone https://github.com/itsnaveenk/Movies-Library2.git
```
2. Change into the directory
```bash
cd Movies-Library2
```
3. Install dependencies
```bash
mvn install
```
4. Run the application
```bash
mvn spring-boot:run
```

## Usage

The application exposes several endpoints for interacting with the movie library:

- `/auth/signup`: Register a new user
- `/auth/login`: Login a user
- `/movies/createlist`: Create a new movie list
- `/movies/mylists`: Fetch the logged-in user's movie lists
- `/movies/search/{title}`: Search for movies by title
- `/movies/addmovie`: Add a movie to a list

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
