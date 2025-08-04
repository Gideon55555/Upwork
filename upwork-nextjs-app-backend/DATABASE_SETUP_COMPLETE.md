# Database Setup Complete ✅

## Current Status

✅ **PostgreSQL Database Created**: `movercrm`  
✅ **Application Configuration Updated**: Using your password `5991`  
✅ **Database URL**: `jdbc:postgresql://localhost:5432/movercrm`  
✅ **Username**: `postgres`  
✅ **Password**: `5991`  

## What's Ready

The database `movercrm` has been created and the application is configured to use it. When you start the Spring Boot application, it will:

1. ✅ Connect to the `movercrm` database
2. ✅ Create all necessary tables automatically
3. ✅ Set up the schema for the CRM system

## To Start the Application

### 1. Start the Backend

```bash
cd upwork-nextjs-app-backend
./mvnw spring-boot:run
```

### 2. Start the Frontend

```bash
cd upwork-nextjs-app
npm run dev
```

### 3. Test the System

1. **Backend API**: Visit `http://localhost:8080/api/swagger-ui.html`
2. **Frontend**: Visit `http://localhost:3000/login`
3. **Database**: The tables will be created automatically on first run

## Database Tables That Will Be Created

When the application starts, it will create these tables:

- `users` - User accounts and authentication
- `clients` - Client information
- `orders` - Order management
- `teams` - Team management
- `transport` - Transport/vehicle management
- `team_members` - Team member relationships
- `order_services` - Order service details

## Manual Database Setup (Optional)

If you want to create a dedicated user for the application, you can run this in pgAdmin or psql:

```sql
-- Connect to PostgreSQL as postgres user
-- Create application user
CREATE USER movercrm_user WITH PASSWORD 'movercrm_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE movercrm TO movercrm_user;

-- Connect to movercrm database
\c movercrm

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO movercrm_user;
```

Then update `application.yml`:
```yaml
spring:
  datasource:
    username: movercrm_user
    password: movercrm_password
```

## Current Configuration

The application is currently configured to use:
- **Database**: `movercrm`
- **User**: `postgres`
- **Password**: `5991`
- **Mode**: `create-drop` (tables recreated on each restart)

## Next Steps

1. **Start the backend**: `./mvnw spring-boot:run`
2. **Start the frontend**: `npm run dev`
3. **Test the integration**: Visit the login page
4. **Create test data**: Use the API to create users and clients

The system is ready to run! 🎉 