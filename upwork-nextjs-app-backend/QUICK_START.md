# Quick Start Guide

## Option 1: Quick Testing with H2 Database (Recommended for Testing)

### 1. Start the Backend with H2 Database

```bash
cd upwork-nextjs-app-backend

# Run with H2 database (no PostgreSQL needed)
./mvnw spring-boot:run -Dspring.profiles.active=h2
```

This will:
- ✅ Start the backend on `http://localhost:8080`
- ✅ Use H2 in-memory database (no installation needed)
- ✅ Create all tables automatically
- ✅ Enable H2 console at `http://localhost:8080/api/h2-console`

### 2. Start the Frontend

```bash
cd upwork-nextjs-app
npm run dev
```

### 3. Test the Integration

1. **Backend API**: Visit `http://localhost:8080/api/swagger-ui.html`
2. **Frontend**: Visit `http://localhost:3000/login`
3. **H2 Database Console**: Visit `http://localhost:8080/api/h2-console`

### 4. Create Test Data

After the backend starts, create a test user:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123",
    "email": "admin@test.com",
    "firstName": "Admin",
    "lastName": "User",
    "role": "ADMIN"
  }'
```

## Option 2: Production Setup with PostgreSQL

### 1. Install PostgreSQL

**Windows:**
- Download from: https://www.postgresql.org/download/windows/
- Install with default settings

**macOS:**
```bash
brew install postgresql
brew services start postgresql
```

### 2. Create Database

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE movercrm;
CREATE USER movercrm_user WITH PASSWORD 'movercrm_password';
GRANT ALL PRIVILEGES ON DATABASE movercrm TO movercrm_user;
\q
```

### 3. Update Configuration

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movercrm
    username: movercrm_user
    password: movercrm_password
```

### 4. Start Backend

```bash
cd upwork-nextjs-app-backend
./mvnw spring-boot:run
```

## Testing the API

### 1. Test Backend API

```bash
cd upwork-nextjs-app-backend
node test-api.js
```

### 2. Test Frontend Integration

1. Start both servers
2. Visit `http://localhost:3000/login`
3. Register/login
4. Test the orders page

## Troubleshooting

### H2 Database Issues
- H2 console: `http://localhost:8080/api/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

### PostgreSQL Issues
- Check if PostgreSQL is running
- Verify connection details in `application.yml`
- Ensure database and user exist

### Frontend Issues
- Check if backend is running on port 8080
- Verify API calls in browser console
- Check CORS configuration

## Next Steps

1. **Test with H2**: Use Option 1 for quick testing
2. **Setup PostgreSQL**: Use Option 2 for production
3. **Add Real Data**: Create clients, orders, teams
4. **Test All Features**: Verify all CRUD operations work 