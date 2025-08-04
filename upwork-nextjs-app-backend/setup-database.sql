-- Database Setup Script for Mover CRM
-- Run this script as postgres user

-- Create the database (if not already created)
-- CREATE DATABASE movercrm;

-- Create application user
CREATE USER movercrm_user WITH PASSWORD 'movercrm_password';

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON DATABASE movercrm TO movercrm_user;

-- Connect to the movercrm database
\c movercrm

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO movercrm_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO movercrm_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO movercrm_user;

-- Set default privileges for future tables
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO movercrm_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO movercrm_user;

-- Verify the setup
\du 