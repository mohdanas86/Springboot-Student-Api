# RestApis

Student management REST API built with Java, Spring Boot, and PostgreSQL.

## Tech Stack

| Component   | Version |
| ----------- | ------- |
| Java        | 21      |
| Spring Boot | 4.0.1   |
| PostgreSQL  | 13+     |
| Maven       | 3.8.1+  |

## Get Started

**Step 1: Create database**
```bash
createdb restapis
```
**Step 2: Set environment variables** — create `.env.local` in project root:
```env
DB_URL=jdbc:postgresql://localhost:5432/restapis
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

**Step 3: Run**
```bash
mvn clean install
mvn spring-boot:run
```

API runs at `http://localhost:8000`

## API

| Method | Endpoint                       | Description  | Status      |
| ------ | ------------------------------ | ------------ | ----------- |
| GET    | `/students`                    | List all     | 200         |
| GET    | `/students/{id}`               | Get by ID    | 200/404     |
| GET    | `/students/by-email?email=...` | Get by email | 200/404     |
| POST   | `/students`                    | Create       | 201/400     |
| PUT    | `/students/{id}`               | Update       | 200/400/404 |
| DELETE | `/students/{id}`               | Delete       | 204/404     |

## Error Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "path": "/students",
  "fieldErrors": {"email": "must be valid"}
}
```

## What's Built

- Full CRUD with proper HTTP status codes
- Input validation (name, email format, unique email)
- Centralized exception handling
- Clean DTOs (no password exposure)

**Author:** Ansa Alam
