# CODECRAFT_BW_04
# User Management System with Redis Caching

## Overview
This project is a **Spring Boot-based User Management System** that uses **Redis** for caching to optimize performance and reduce database query load. It provides REST APIs for managing users, including functionalities like creating, retrieving, updating, and deleting users.

### **Key Features**
- **Caching with Redis**: Reduce API response times by caching frequently accessed data.
- **API Functionalities**:
  - **Create a new user**: Save the user to the database and cache the result.
  - **Retrieve all users**: Return cached data for subsequent requests.
  - **Retrieve a user by ID**: Fetch from cache if available, else query the database.
  - **Update a user**: Refresh the cache after updating the database.
  - **Delete a user**: Clear cache entries when a user is deleted.
- **Data Entity**: `User` with fields `name`, `age`, and `email`.

---

## Performance Improvement with Redis
Redis significantly reduces response times by caching frequently accessed data in memory. Without Redis, responses require database queries, which are slower compared to in-memory retrieval.

### **Benchmark Results**
| Operation        | Without Redis (DB Query) | With Redis (Cached Data) |
|-------------------|---------------------------|---------------------------|
| **Get All Users** | 300-400 ms               | 10-15 ms                 |
| **Get User by ID**| 200-300 ms               | 10-15 ms                 |

The reduction in response times (~95%) demonstrates the impact of Redis caching on API performance.

---

## API Endpoints

### **User Endpoints**
| Method | Endpoint          | Description                 | Requires Redis |
|--------|------------------|----------------------------|----------------|
| POST   | `/api/users`      | Create a new user          | ✅ Yes         |
| GET    | `/api/users`      | Get all users              | ✅ Yes         |
| GET    | `/api/users/{id}` | Get user by ID             | ✅ Yes         |
| PUT    | `/api/users/{id}` | Update user by ID          | ✅ Yes         |
| DELETE | `/api/users/{id}` | Delete user by ID          | ✅ Yes         |

---

## Installation & Setup

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/your-repo-url.git
cd your-project
