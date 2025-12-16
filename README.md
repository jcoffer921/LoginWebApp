# LoginWebApp
Java JSP/Servlet web application deployed as a WAR on GlassFish with an embedded Apache Derby database.

## Tech Stack
- Java (JDK 17 recommended)
- JSP / Servlets (Jakarta Servlet API)
- GlassFish Server
- Apache Derby (Embedded)
- Maven

## Features
- Admin login
- Product CRUD (Create, Read, Update, Delete)
- Embedded Derby database persistence (auto-creates DB + tables on first run)
- Session-based access control

## How to Run 
### Deploy the included WAR 
1. Start GlassFish.
2. Deploy the WAR from: (`target/LoginWebApp-1.0-SNAPSHOT.war`)
3. Open the app in a browser:
   - `http://localhost:8080/LoginWebApp/` (default GlassFish HTTP port)

## Database Notes 
- This project uses **embedded Apache Derby**.
- The database and tables are **created automatically** on first run (no manual SQL setup required).
- The DB is stored in the user’s home directory (portable across machines).

## Troubleshooting
### Port 8080 already in use
If GlassFish fails to start because 8080 is in use, either:
- Change GlassFish HTTP port (e.g., to 8081), then open:
  `http://localhost:8081/LoginWebApp/`
OR
- Stop the process using 8080 (Windows PowerShell):
  ```powershell
  netstat -ano | findstr :8080
  taskkill /PID <PID> /F
