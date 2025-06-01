# Twitter Clone Backend

This repository contains the backend implementation of a Twitter-like social media platform. Built with Java and Spring Boot, it provides RESTful APIs to support functionalities such as user authentication, tweet management, and user interactions.

## 🚀 Features

- User registration and authentication
- Tweet creation, retrieval, and deletion
- User follow and unfollow functionality
- Like and retweet features
- Real-time notifications using WebSockets
- Search functionality for tweets and users

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- WebSockets for real-time communication

## 📦 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/TwitterClone-KTHDV/twitter-clone-be.git
   cd twitter-clone-be
   ```

2. Configure the database:
   - Create a MySQL database named `twitter_clone`.
   - Update the `application.properties` file with your MySQL credentials.

3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## 📁 Project Structure

```
twitter-clone-be/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── twitterclone/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           ├── service/
│   │   │           └── TwitterCloneApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
├── pom.xml
└── README.md
```

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 🙌 Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [WebSockets](https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API)
