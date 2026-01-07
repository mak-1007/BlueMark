# 📱 BlueMark – Bluetooth-Based Attendance Management System

Managing attendance in educational institutions is still largely a manual process, which often leads to inefficiencies, human errors, paperwork burden, difficulty in maintaining past records, and lack of instant access to attendance data.

**BlueMark** is an Android-based attendance management application designed to overcome these challenges by enabling teachers or administrators to mark student attendance automatically using **Bluetooth-based proximity detection**.

---

## 📌 Project Overview

BlueMark modernizes the traditional attendance system by using **Bluetooth Classic** to detect nearby student devices and mark attendance during an active session.

The system eliminates manual roll calls and provides a **contactless, fast, and reliable** attendance solution for classrooms and academic environments.

Teachers or administrators can start an attendance session, and nearby student devices are detected automatically, ensuring quick and efficient attendance marking.

---

## 🎯 Objectives

- Identify limitations of manual attendance systems  
- Design and develop an automated mobile attendance solution  
- Reduce human errors and paperwork  
- Enable faster attendance marking  
- Improve accuracy, accessibility, and efficiency  

---

## ✨ Key Features

- 📡 **Bluetooth-based real-time student device detection**
- 🧑‍🏫 **Teacher/Admin-only access**
- 📊 **Live attendance view during scanning**
- 🗂️ **Offline attendance storage using Room Database**
- 📜 **Attendance history and logs**
- 🔐 **Permission-secured system (Bluetooth & Location)**
- 🎨 **Modern UI using Jetpack Compose**

---

## 🛠️ Tech Stack

### 🔹 Programming Language
- **Kotlin**

### 🔹 IDE & Build
- Android Studio  
- Gradle (Kotlin DSL)

### 🔹 Architecture
- **MVVM (Model–View–ViewModel)**
- Repository Pattern
- Clean Architecture inspired structure

### 🔹 UI Design
- **Jetpack Compose**
- Material Design (Material 3)
- XML-free UI

### 🔹 Connectivity
- **Bluetooth Classic**
- Android Bluetooth API
- BroadcastReceiver for device discovery

### 🔹 Database
- **Room Database**
- SQLite (under the hood)

### 🔹 Dependency Injection
- **Dagger Hilt**

### 🔹 Version Control
- Git & GitHub

---

## ⚙️ System Requirements

### 🔹 Hardware
- **Minimum:** Android device with Bluetooth support, 4GB RAM  
- **Recommended:** 6GB+ RAM device for smoother performance  

### 🔹 Software
- Android 
- Android Studio (latest version)  
- Kotlin & Gradle  

---

## 🚀 App Workflow

1. Launch the BlueMark app  
2. Teacher/Admin accesses the app  
3. Permission confirmation (Bluetooth & Location)  
4. Start attendance session  
5. Live Bluetooth scanning begins  
6. Nearby student devices are detected  
7. Attendance is marked automatically  
8. Attendance records are stored locally  
9. Attendance history can be viewed later  

---

## 📸 App Screenshots & Demo

Screenshots are available in the **screenshots/** folder and demonstrate the complete application flow:

- Splash Screen  
- Login & Permission Screens  
- Permission Confirmation  
- Home & Dashboard  
- Dashboard (Before Scanning)  
- Attendance Scanning (Live Detection)  
- Present Students List  
- Attendance Reports  
- Notifications & Profile  

---

## 🔮 Future Scope & Enhancements

- **Face Recognition–Based Attendance**
  - Prevent proxy attendance by verifying physical presence
  - Head count detection using camera-based face detection

- **Secure Authentication**
  - Email/OTP-based login

- **Cloud Integration**
  - Firebase-based cloud backup
  - Multi-device data synchronization

- **AI-Based Proxy Detection**
  - Combine Bluetooth + Face Recognition for higher accuracy

- **Advanced Analytics**
  - Attendance trends and reports
  - Export data as PDF or Excel

---

## 👨‍💻 Developer

**Aman**  
Final Year B.Tech (Computer Science & Engineering)  
Android App Development Project  

---

⭐ If you find this project useful, feel free to star the repository!

<!-- =================== BLUEMARK APP SCREENS (ORDERED FLOW) =================== -->

<p align="center">
  <!-- Row 1 -->
  <img src="https://github.com/user-attachments/assets/48340ff4-107e-4f3c-8e28-d8814edc83bb" width="160" alt="01 Splash Screen"/>
  <img src="https://github.com/user-attachments/assets/062ceaac-9ca8-4f44-8802-e13b3a356efb" width="160" alt="02 Login Screen"/>
  <img src="https://github.com/user-attachments/assets/6faa6e20-df22-4a72-b090-c4c0cbe68c4d" width="160" alt="04 Permission Confirmation"/>
  <img src="https://github.com/user-attachments/assets/ed90a774-ebf0-4629-99cc-b5005a03a7da" width="160" alt="05 Bluetooth Permission"/>
</p>

<p align="center">
  <!-- Row 2 -->
  <img src="https://github.com/user-attachments/assets/e1274cce-1c20-4e80-93af-a636a5efd12c" width="160" alt="07 Dashboard Before Scan"/>
  <img src="https://github.com/user-attachments/assets/ec7f0fa2-7d04-4178-b3e5-28a77b4bd2aa" width="160" alt="08 Scanning Screen"/>
  <img src="https://github.com/user-attachments/assets/6080cae5-941e-42c0-8d32-69a17a46d57f" width="160" alt="10 Paired Devices History"/>
  <img src="https://github.com/user-attachments/assets/ab2c3c82-f46e-43e5-b8c8-12d18a290fe7" width="160" alt="11 Present Students"/>
</p>

<!-- =================== END OF SCREENSHOTS =================== -->











