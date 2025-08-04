# Database Setup Guide

## Option 1: Local PostgreSQL Installation

### 1. Install PostgreSQL

#### Windows:
1. Download PostgreSQL from: https://www.postgresql.org/download/windows/
2. Run the installer and follow the setup wizard
3. Remember the password you set for the `postgres` user
4. Default port is 5432

#### macOS:
```bash
brew install postgresql
brew services start postgresql
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

### 2. Create Database and User

Connect to PostgreSQL as the postgres user:

```bash
# Windows (if added to PATH)
psql -U postgres

# macOS/Linux
sudo -u postgres psql
```

Create the database and user:

```sql
-- Create database
CREATE DATABASE movercrm;

-- Create user
CREATE USER movercrm_user WITH PASSWORD 'movercrm_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE movercrm TO movercrm_user;

-- Connect to the database
\c movercrm

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO movercrm_user;

-- Exit
\q
```

### 3. Update Application Configuration

Update `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movercrm
    username: movercrm_user
    password: movercrm_password
    driver-class-name: org.postgresql.Driver
```

## Option 2: Using Docker (if available later)

If Docker becomes available, you can use the provided `docker-compose.yml`:

```bash
cd upwork-nextjs-app-backend
docker compose up -d
```

This will start PostgreSQL on port 5432 with the database `movercrm`.

## Option 3: Cloud Database (Recommended for Production)

### PostgreSQL on Railway:
1. Go to https://railway.app/
2. Create account and new project
3. Add PostgreSQL service
4. Copy connection details to `application.yml`

### PostgreSQL on Supabase:
1. Go to https://supabase.com/
2. Create account and new project
3. Go to Settings > Database
4. Copy connection details to `application.yml`

## Running the Application

1. Start the database (PostgreSQL)
2. Run the Spring Boot application:

```bash
cd upwork-nextjs-app-backend
./mvnw spring-boot:run
```

The application will automatically create the database tables on first run.

## Initial Data Setup

After the application starts, you can create an admin user via the API:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123",
    "email": "admin@movercrm.com",
    "firstName": "Admin",
    "lastName": "User",
    "role": "ADMIN"
  }'
```

## Troubleshooting

### Connection Issues:
- Check if PostgreSQL is running
- Verify port 5432 is not blocked
- Check firewall settings
- Verify username/password in application.yml

### Permission Issues:
- Ensure the user has proper privileges
- Check database ownership
- Verify schema permissions

### Port Conflicts:
- Change PostgreSQL port in postgresql.conf
- Update application.yml accordingly 