# 🚨 Disaster Management System

A web-based disaster management platform built with **Spring Boot**, **MySQL**, and **Thymeleaf**. The system allows users to register, report disasters, and track their reports, while admins can manage all reports, update their statuses, and oversee the user base.


## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)


## ✨ Features

### 👤 User
- Register and log in via a shared login page
- Submit disaster reports
- View all personal reports and their current status

### 🛡️ Admin
- Log in via the same shared login page (role-based redirect after login)
- Add new admin accounts
- View all registered users
- View and manage all disaster reports
- Update report statuses (e.g. Pending → Approved)


## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Frontend | Thymeleaf, HTML, CSS |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security |
| Build Tool | Maven (via Maven Wrapper) |
