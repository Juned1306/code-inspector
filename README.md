# Code Inspector

**AI-powered Code Review Tool** built with **Spring Boot** (backend) and **React.js** (frontend) to analyze, inspect, and suggest improvements for source code using **Gemini AI**.  

---

## Features

- Inspect and review source code for **errors**, **best practices**, and **performance improvements**.  
- Provides **corrected code**, **error highlights**, and **suggestions**.  
- Maintains **history of code inspections** (coming soon in next updates).  
- **Frontend** with live code editor, syntax highlighting, and dark/light theme.  
- **Copy-corrected code** functionality with a single click.  
- **Backend** powered by **Gemini AI** for accurate code analysis.  
- Fully documented APIs with **Swagger/OpenAPI**.  

---

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Swagger/OpenAPI, Gemini AI SDK  
- **Frontend:** React.js, React Simple Code Editor, Prism.js  
- **Database:**  MySQL (configurable in `application.properties`)  
- **Others:** Axios for API calls, CORS enabled  

---

## Getting Started

### Prerequisites

- Java 17+  
- Maven  
- Node.js 18+ and npm  
- Git  

---

### Environment Variables

The backend uses **Gemini AI** for code inspection. Set your API key in environment variables:

```bash
 Windows (PowerShell)
$env:GOOGLE_API_KEY="your_gemini_api_key_here"

# Navigate to project root (where pom.xml is located)
mvn clean spring-boot:run

# Navigate to frontend folder
cd code-inspector-frontend
npm install
npm start


Swagger API Documentation
Access Swagger UI after starting the backend:

Swagger URL: http://localhost:8080/swagger-ui.html

Available APIs:

Inspect Code: POST /api/code

History: GET /api/history (coming soon)

Swagger provides request/response models, input validation, and interactive testing.

