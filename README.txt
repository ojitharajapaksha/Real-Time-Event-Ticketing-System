Real-Time Event Ticketing System

Overview

The Real-Time Event Ticketing System simulates a dynamic ticketing environment in which customers buy tickets at the same time that vendors add them. With Spring Boot for the backend and React for the frontend, the system demonstrates the producer-consumer design with multi-threading and synchronization.

Features

Configure ticketing parameters (total tickets, ticket release rate, ticket retrieval rate and max ticket capacity).
Real-time ticket status updates.
Start/stop ticketing operations dynamically.
Manage and display logs of ticket transactions.
Responsive UI for easy to use.

Technologies

Backend: Spring Boot
Frontend: React.js
Database: MySQL

Usage Instructions

1. Open the application in your browser at http://localhost:3000.
2. Configure the system using the Configuration form.
3. Start the system using the control panel.
4. View real-time ticket status and logs.
5. Stop the system or clear logs if you needed.

Endpoints

1. POST /api/configure: Configure the ticketing system.
2. POST /api/start: Start the system.
3. POST /api/stop: Stop the system.
4. GET /api/logs: Retrieve logs.
5. DELETE /api/logs: Clear all logs.
6. GET /api/ticket-count: Get current ticket count.