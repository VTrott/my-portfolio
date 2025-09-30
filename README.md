# Portfolio Website

A modern, responsive portfolio website built with Angular frontend and Java Spring Boot backend.

## Features

- **Frontend (Angular)**: Modern, responsive UI with Angular Material
- **Backend (Java Spring Boot)**: RESTful API with JPA/Hibernate
- **Database**: H2 (development) / MySQL (production)
- **Authentication**: JWT-based authentication
- **Project Management**: CRUD operations for portfolio projects
- **Responsive Design**: Mobile-first approach

## Tech Stack

### Frontend
- Angular 17+
- Angular Material
- TypeScript
- SCSS
- RxJS

### Backend
- Java 17
- Spring Boot 3.2+
- Spring Security
- Spring Data JPA
- H2 Database (dev) / MySQL (prod)
- Maven

## Project Structure

```
portfolio-website/
├── frontend/                 # Angular frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/   # Reusable components
│   │   │   ├── services/     # API services
│   │   │   ├── models/       # TypeScript interfaces
│   │   │   └── guards/       # Route guards
│   │   └── assets/           # Static assets
│   └── package.json
├── backend/                  # Spring Boot backend
│   ├── src/main/java/
│   │   └── com/portfolio/backend/
│   │       ├── controller/   # REST controllers
│   │       ├── service/      # Business logic
│   │       ├── repository/   # Data access
│   │       ├── model/        # JPA entities
│   │       └── config/       # Configuration
│   └── pom.xml
└── README.md
```

## Getting Started

### Prerequisites
- Node.js 18+ and npm
- Java 17+
- Maven 3.6+

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd portfolio-website
   ```

2. **Start the Backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8080/api`

3. **Start the Frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```
   The application will be available at `http://localhost:4200`

## API Endpoints

- `GET /api/projects` - Get all projects
- `POST /api/projects` - Create a new project
- `PUT /api/projects/{id}` - Update a project
- `DELETE /api/projects/{id}` - Delete a project
- `GET /api/about` - Get about information
- `POST /api/contact` - Send contact message

## Development

### Frontend Development
- Run `ng generate component <name>` to generate new components
- Run `ng generate service <name>` to generate new services
- Run `ng test` to run unit tests
- Run `ng e2e` to run end-to-end tests

### Backend Development
- Run `mvn test` to run unit tests
- Access H2 console at `http://localhost:8080/api/h2-console`
- Use `mvn clean package` to build the application

## Deployment

The application can be deployed to various platforms:
- **Frontend**: Vercel, Netlify, AWS S3 + CloudFront
- **Backend**: Heroku, AWS EC2, Google Cloud Run
- **Database**: AWS RDS, Google Cloud SQL, or any MySQL provider

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.
