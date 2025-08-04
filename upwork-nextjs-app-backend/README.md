# Mover CRM Backend

A comprehensive Spring Boot backend API for the Mover CRM system, providing authentication, order management, client management, team management, and transport management functionalities.

## 🚀 Features

### Authentication & Authorization
- JWT-based authentication
- User registration and login
- Role-based access control (ADMIN, MANAGER, OPERATOR, DRIVER)
- Password encryption with BCrypt

### Core Functionalities
- **User Management**: Create, read, update, delete users
- **Client Management**: Manage client information with search and favorites
- **Order Management**: Complete order lifecycle with status tracking
- **Team Management**: Organize teams with leaders and members
- **Transport Management**: Vehicle and driver assignment
- **Calendar Integration**: Order scheduling and date-based queries

### API Features
- RESTful API design
- Pagination support
- Search functionality
- Filtering by various criteria
- CORS configuration for frontend integration
- Swagger/OpenAPI documentation

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Security**: Spring Security with JWT
- **Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🗄️ Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE mover_crm;
```

2. Update database configuration in `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mover_crm
    username: your_username
    password: your_password
```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd upwork-nextjs-app-backend
```

### 2. Configure Application
Update `src/main/resources/application.yml` with your database credentials and JWT secret.

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

Once the application is running, you can access:
- **Swagger UI**: `http://localhost:8080/api/swagger-ui.html`
- **OpenAPI Docs**: `http://localhost:8080/api/api-docs`

## 🔐 Authentication

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

### Register
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "newuser",
  "email": "user@example.com",
  "password": "password",
  "firstName": "John",
  "lastName": "Doe",
  "role": "OPERATOR"
}
```

## 📊 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/me` - Get current user
- `POST /api/auth/logout` - User logout

### Clients
- `GET /api/clients` - Get all clients
- `GET /api/clients/{id}` - Get client by ID
- `POST /api/clients` - Create new client
- `PUT /api/clients/{id}` - Update client
- `DELETE /api/clients/{id}` - Delete client
- `GET /api/clients/search?searchTerm=...` - Search clients
- `GET /api/clients/favorites` - Get favorite clients
- `PUT /api/clients/{id}/favorite` - Toggle favorite status

### Orders
- `GET /api/orders` - Get all orders (paginated)
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}` - Update order
- `DELETE /api/orders/{id}` - Delete order
- `GET /api/orders/search?searchTerm=...` - Search orders
- `GET /api/orders/status/{status}` - Get orders by status
- `GET /api/orders/date/{date}` - Get orders by date
- `GET /api/orders/date-range?startDate=...&endDate=...` - Get orders by date range
- `GET /api/orders/client/{clientId}` - Get orders by client
- `GET /api/orders/team/{teamId}` - Get orders by team
- `GET /api/orders/transport/{transportId}` - Get orders by transport
- `PUT /api/orders/{id}/status` - Update order status

### Teams
- `GET /api/teams` - Get all teams
- `GET /api/teams/{id}` - Get team by ID
- `POST /api/teams` - Create new team
- `PUT /api/teams/{id}` - Update team
- `DELETE /api/teams/{id}` - Delete team
- `GET /api/teams/search?searchTerm=...` - Search teams

### Transport
- `GET /api/transport` - Get all transport
- `GET /api/transport/{id}` - Get transport by ID
- `POST /api/transport` - Create new transport
- `PUT /api/transport/{id}` - Update transport
- `DELETE /api/transport/{id}` - Delete transport
- `GET /api/transport/search?searchTerm=...` - Search transport
- `GET /api/transport/available` - Get available transport

## 🏗️ Project Structure

```
src/main/java/com/upwork/movercrm/
├── config/                 # Configuration classes
│   └── SecurityConfig.java
├── controller/            # REST controllers
│   ├── AuthController.java
│   ├── ClientController.java
│   ├── OrderController.java
│   ├── TeamController.java
│   └── TransportController.java
├── dto/                   # Data Transfer Objects
│   ├── LoginRequest.java
│   └── LoginResponse.java
├── entity/               # JPA entities
│   ├── User.java
│   ├── Client.java
│   ├── Order.java
│   ├── Team.java
│   ├── Transport.java
│   ├── TeamMember.java
│   └── OrderService.java
├── repository/           # Data access layer
│   ├── UserRepository.java
│   ├── ClientRepository.java
│   ├── OrderRepository.java
│   ├── TeamRepository.java
│   └── TransportRepository.java
├── security/            # Security components
│   ├── JwtUtils.java
│   └── JwtAuthenticationFilter.java
├── service/             # Business logic
│   ├── AuthService.java
│   └── UserDetailsServiceImpl.java
└── MoverCrmApplication.java
```

## 🔧 Configuration

### JWT Configuration
```yaml
jwt:
  secret: your-super-secret-jwt-key-here-make-it-very-long-and-secure
  expiration: 86400000 # 24 hours in milliseconds
```

### CORS Configuration
```yaml
cors:
  allowed-origins: http://localhost:3000
  allowed-methods: GET,POST,PUT,DELETE,OPTIONS
  allowed-headers: "*"
  allow-credentials: true
```

## 🧪 Testing

Run tests with:
```bash
mvn test
```

## 📦 Deployment

### Docker
```bash
# Build Docker image
docker build -t mover-crm-backend .

# Run container
docker run -p 8080:8080 mover-crm-backend
```

### Production
1. Update `application.yml` for production settings
2. Set environment variables for sensitive data
3. Configure database connection
4. Deploy to your preferred platform (AWS, Azure, GCP, etc.)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions, please contact the development team or create an issue in the repository. 