# Real-Time Event Ticketing System

## Overview
The Real-Time Event Ticketing System simulates a dynamic ticketing environment where customers purchase tickets while vendors add them in real time. Built using Spring Boot for the backend and React.js for the frontend, this system demonstrates the producer-consumer design with multi-threading and synchronization to ensure smooth ticket management.

## Features
- Configure ticketing parameters, including total tickets, ticket release rate, ticket retrieval rate, and maximum ticket capacity.
- Real-time ticket status updates.
- Start and stop ticketing operations dynamically.
- Manage and display logs of ticket transactions.
- User-friendly and responsive interface.

## Technologies Used
- Backend: Spring Boot (Java)
- Frontend: React.js
- Database: MySQL

## Usage Instructions
1. Open the application in your browser at **http://localhost:3000**.
2. Configure the system using the configuration form.
3. Start the system using the control panel.
4. Monitor real-time ticket status and transaction logs.
5. Stop the system or clear logs if needed.

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/configure` | Configure the ticketing system. |
| POST   | `/api/start` | Start the ticketing operations. |
| POST   | `/api/stop` | Stop the ticketing operations. |
| GET    | `/api/logs` | Retrieve transaction logs. |
| DELETE | `/api/logs` | Clear all transaction logs. |
| GET    | `/api/ticket-count` | Get the current ticket count. |

## Future Enhancements
- Implement an admin dashboard for better control over ticketing operations.
- Introduce role-based access for vendors and customers.
- Add a notification system for ticket availability and sales updates.
- Improve scalability to support large-scale event management.
