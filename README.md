# 🔐 Password Breach Protector (Android)

A privacy-first Android app built with **Kotlin** and **Jetpack Compose** that helps users check if their passwords have appeared in known data breaches — **without ever sending the full password to any server**.

---

## 🚀 Overview
This app uses the [Have I Been Pwned](https://haveibeenpwned.com/API/v3#PwnedPasswords) k-anonymity API to check password breach counts securely.  
Your password is hashed locally (SHA-1), and only the first 5 characters of the hash are sent to the API.  
All results are processed on-device — no sensitive data leaves your phone.

---

## 🧩 Core Features (MVP)
- 🔒 **Breach Check:** Client-side hashing with k-anonymity lookup  
- 📊 **Result Summary:** See how many times a password appears in breaches  
- 🧠 **Strength Meter:** Basic entropy-based password strength score  
- 🗂️ **Optional Local History:** Encrypted record of past checks  
- ⚙️ **Settings Page:** Privacy policy, telemetry opt-in/out  
- 🧾 **Offline-First UI:** Works gracefully even without internet connection  

---

## 🏗️ Tech Stack
- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** MVVM (ViewModel + StateFlow)  
- **DI:** Hilt  
- **Networking:** Retrofit + OkHttp + Coroutines  
- **Storage:** EncryptedSharedPreferences (Room optional)  
- **Testing:** JUnit, Espresso, MockWebServer  
- **CI/CD:** GitHub Actions (build → test → release)

---

## 📦 Project Structure
