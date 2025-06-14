# Student Management System (FS-APP)

## Project Overview
This is a fullstack application for managing student data and their grades. The backend is built with Spring Boot and provides a RESTful API for performing CRUD operations on students and their grades. The application includes JWT-based authentication and role-based access control.

## Technologies Used
- **Java 17**
- **Spring Boot 3.x**
- **Spring Security** with JWT authentication
- **Spring Data JPA** for database access
- **MySQL** as the database
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management

## Project Structure
```
src/main/java/com/esgitech/fsapp/
├── controllers/           # REST API controllers
│   ├── EtudiantController.java
│   └── NoteController.java
├── DTO/                   # Data Transfer Objects
│   └── EtudiantDTO.java
├── enums/                 # Enumerations
│   └── NiveauEtude.java
├── exceptions/            # Custom exceptions
│   └── EtudiantNotFoundException.java
├── execute/               # Data initialization
│   └── EtudiantDataInitializer.java
├── model/                 # Domain models
│   ├── Etudiant.java
│   └── Note.java
├── repository/            # Data repositories
│   ├── EtudiantRepository.java
│   └── NoteRepository.java
├── security/              # Security configuration
│   ├── SecurityConfig.java
│   └── SecurityController.java
├── services/              # Business logic
│   ├── EtudiantService.java
│   └── NoteService.java
└── FsAppApplication.java  # Main application class
```

## Database Schema
The application uses two main entities:
1. **Etudiant (Student)**
   - id: Primary key
   - nom: Last name
   - prenom: First name
   - email: Email address
   - codeEtudiant: Unique student code
   - niveauEtude: Study level (LICENCE_1, LICENCE_2, LICENCE_3, MASTER_1, MASTER_2, DOCTORAL)
   - notes: One-to-many relationship with Note

2. **Note (Grade)**
   - id: Primary key
   - matiere: Subject name
   - valeur: Grade value
   - etudiant: Many-to-one relationship with Etudiant

## Setup Instructions
1. **Prerequisites**
   - Java 17 or higher
   - Maven
   - MySQL

2. **Database Configuration**
   - Create a MySQL database named `fs-app`
   - Update the database connection details in `application.properties` if needed:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/fs-app?useSSL=false
     spring.datasource.username=root
     spring.datasource.password=
     ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The application will start on port 8095 by default.

4. **Initial Data**
   The application is pre-configured to initialize the database with sample data on startup.

## Security
The application uses JWT-based authentication with the following features:
- Token-based authentication
- Role-based access control
- Stateless sessions
- CORS configuration for frontend access

### Authentication
1. **Login**
   - Endpoint: `POST /auth/login`
   - Parameters: `username`, `password`
   - Response: JWT token valid for 10 minutes

2. **User Roles**
   - USER: Can view student data
   - ADMIN: Can create, update, and delete student data

3. **Default Users**
   - Username: `user1`, Password: `12345`, Role: `USER`
   - Username: `admin`, Password: `12345`, Role: `USER, ADMIN`

## API Endpoints

### Authentication
- `POST /auth/login` - Authenticate and get JWT token
- `GET /auth/profile` - Get current user profile

### Students (Etudiants)
- `GET /api/etudiants` - Get all students (requires USER role)
- `GET /api/etudiants/{id}` - Get student by ID (requires USER role)
- `POST /api/etudiants` - Create a new student
- `POST /api/etudiants/add-students` - Add multiple students (requires ADMIN role)
- `PUT /api/etudiants/{id}` - Update student by ID (requires ADMIN role)
- `PUT /api/etudiants/updateByCodeEtudiant` - Update student by code (requires ADMIN role)
- `DELETE /api/etudiants/{code}` - Delete student by code (requires ADMIN role)
- `GET /api/etudiants/filteredStudents` - Filter students by name or first name (requires USER role)

### Grades (Notes)
- `GET /notes` - Get all grades
- `GET /notes/{id}` - Get grade by ID
- `GET /notes/etudiant/{codeEtudiant}` - Get all grades for a student by code
- `POST /notes/etudiant/{codeEtudiant}` - Add a new grade for a student
- `PUT /notes/{id}` - Update grade by ID
- `DELETE /notes/{id}` - Delete grade by ID

## Frontend Integration
The backend is configured to allow CORS from `http://localhost:4200`, which is the default port for Angular applications.
