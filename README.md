# ⚽ Sports Events System

A CRUD system for managing sports events, implementing operations on matches, teams, stadiums, divisions, and cities. This system includes both backend REST API and a frontend application built with `Angular`.
The backend API is developed using `Java` and `Spring Boot`, with Hibernate for object-relational mapping. `MySQL` is used as the database. Design patterns such as MVC, DTO, Repositories, and Dependency Injection are implemented, along with exception handling.

---

## Index

* [🌐 Frontend](#-frontend)

  * [Installation](#frontend-installation)
  * [Usage](#frontend-usage)
  * [Features Display](#features-display)
  * [Event Request Booking](#event-request-booking)
* [⚙ Backend](#-backend)

  * [Installation](#installation)
  * [ER Model](#er-model)
  * [API Endpoints](#api-endpoints)
* [📄 Event Request Booking](#event-request-booking)

---

# 🌐 Frontend

## Frontend Installation

1. Navigate to the frontend directory within the project.
2. Install dependencies using `npm` or `yarn`.
3. Run the development server:

```bash
ng serve
```

Access the frontend at:
[http://localhost:4200](http://localhost:4200)

---

## Frontend Usage

Once the frontend and backend are running, access the application through your browser. The UI allows you to:

* Create and manage cities, stadiums, teams, divisions, and matches
* Book new event requests using a user-facing form
* View and update all records dynamically
* Benefit from form validation and error handling

---

## Features Display

### 🎟 Create Event

![create-event](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/6976aebd-de63-4528-bd65-fa33b9fac72a)

### 🗕 Event Request Booking

Book a new sports event by selecting home/away teams, date/time, and match details.

![event-request-booking](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/YOUR_IMAGE_ID_HERE)

### 👥 Create Team

![create-team](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/ab842581-67ca-429d-943e-3f235291fc93)

### 🏩 Create City

![create-city](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/a074b796-2cfa-4491-9242-649dde45ce95)

### 🏟 Create Stadium

![create-stadium](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/74b7818a-d3fc-451a-a110-772ce9765af6)

### 🏆 Create Division

![create-division](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/c80d3e9f-d786-4c7d-a258-4986f31d9610)

### ⚡ Reactive Rendering

![reactive-rendering](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/7bd1108b-a872-4e3d-9edf-49e6e36b21df)

### ⚠️ Error Popups

![error-handling](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/64111775-fa5f-47dd-add9-562bfa9d37c2)

### ✅ Input Validation

![validation](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/1f032a42-424a-4576-beb6-10e609e752d5)

---

# ⚙ Backend

## Installation

1. Clone the repository:

```bash
git clone https://github.com/Digeesha/Sports-Events-System.git
```

2. Open the project in your IDE (e.g., IntelliJ, Eclipse, STS).

3. Update database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/your-db-name?useSSL=false&serverTimeZone=UTC
spring.datasource.username=your-username
spring.datasource.password=your-password
```

4. Create the database using the SQL script:

```bash
src/main/resources/scripts/bd_script.sql
```

5. Run the Spring Boot application using the main class `MatchescrudApplication`.

---

## ER Model

![ER Model](https://github.com/Digeesha/Sports-Events-CRUD/assets/86586819/8409d484-4e3a-4c96-b276-697551988f5a)

---

## API Endpoints

* See full endpoint documentation in respective sections for:

  * Team
  * City
  * Stadium
  * Division
  * Match

The new match creation endpoint is reused for Event Requests:

```http
POST /api/v1/match
```

---

## 📄 Event Request Booking

The **Event Request Booking** page allows users to submit match requests without needing direct access to admin tools.

### Form Inputs:

* Home Team
* Away Team
* Date
* Time
* Spectators (optional)
* Ticket Price (optional)

### Key Behavior:

* Stadium is auto-assigned from home team.
* Match revenue is auto-calculated as: `spectators * ticket price`.
* Request is sent to:
  `POST /api/v1/match`

This feature streamlines match scheduling and allows user-initiated events in a clean, validated flow.

---

