<div align="center">

# ⚔️ Productive Heroes ⚔️

### *Transform Your Tasks into Epic Quests!*

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-brightgreen.svg?style=for-the-badge&logo=jetpack-compose)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/Android-API%2024+-green.svg?style=for-the-badge&logo=android)](https://android.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

---

**Bosan dengan to-do list yang membosankan?** 🥱  
**Productive Heroes** mengubah produktivitas Anda menjadi petualangan RPG yang seru! Selesaikan quest, kalahkan monster, dan naikkan level karakter Anda sambil menyelesaikan tugas sehari-hari! 🎮✨

[✨ Features](#-fitur-utama) • [🚀 Installation](#-instalasi) • [🎯 How to Use](#-cara-penggunaan) • [🛠️ Tech Stack](#️-tech-stack) • [📸 Screenshots](#-screenshots)

</div>

---

## ✨ Fitur Utama

### 🗡️ **Quest Management System**
- 📝 **Tambah Quest dengan Mudah** - Buat tugas baru dengan cepat dan intuitif
- ✅ **Selesaikan & Hapus Quest** - Tandai quest sebagai selesai atau hapus yang tidak perlu
- 🎨 **UI Fantasi yang Menarik** - Desain bergaya RPG yang membuat manajemen tugas jadi menyenangkan
- 💾 **Auto-Save** - Semua quest tersimpan otomatis menggunakan DataStore

### ⚔️ **Combat & Timer System**
- 🐉 **Lawan 4 Monster Unik**:
  - 👻 **Slime Ghost** (900 HP, +50 EXP)
  - 🦎 **Baby Lizard** (1,200 HP, +100 EXP)
  - 🌳 **Evil Tree** (1,500 HP, +150 EXP)
  - 👤 **The Unknown** (1,800 HP, +200 EXP)
- ⏱️ **Real-time Combat Timer** - Serang monster secara otomatis dengan sistem timer
- 🛡️ **Stamina System** - Kelola stamina untuk bertarung lebih lama
- 💤 **Rest & Recovery** - Istirahatkan karakter untuk memulihkan stamina

### 📊 **Character Progression**
- 🆙 **Level Up System** - Dapatkan EXP dari menyelesaikan quest dan mengalahkan monster
- 📈 **Exp Bar** - Tracking progress menuju level berikutnya secara visual
- 💪 **Stat Growth** - Stats karakter meningkat seiring level bertambah
- 🏆 **Persistent Progress** - Progress tersimpan bahkan setelah menutup aplikasi

### 🎮 **Modern UI/UX**
- 🖼️ **Fantasy Theme** - Desain arena pertempuran yang imersif
- 📱 **Responsive Layout** - Optimized untuk berbagai ukuran layar
- 🎨 **Custom Fonts** - Jersey25 dan Pixelify Sans untuk nuansa retro gaming
- 🔄 **Smooth Animations** - Transisi dan animasi yang halus menggunakan Compose

---

## 🚀 Instalasi

### Prerequisites
- 📱 **Android Studio** (Ladybug 2024.2.1 atau lebih baru)
- ☕ **JDK 11** atau lebih tinggi
- 🤖 **Android SDK API 24+** (Android 7.0 Nougat atau lebih baru)
- 📦 **Gradle 8.13**

### Langkah Instalasi

1. **Clone Repository**
   ```bash
   git clone https://github.com/azwinrx/Task-Hero.git
   cd Task-Hero
   ```

2. **Buka di Android Studio**
   - Pilih `File` → `Open`
   - Navigasi ke folder project
   - Klik `OK` dan tunggu Gradle sync selesai

3. **Build & Run**
   - Hubungkan device Android atau jalankan emulator
   - Klik tombol `Run` (▶️) di Android Studio
   - Atau gunakan shortcut `Shift + F10`

4. **Build APK (Opsional)**
   ```bash
   ./gradlew assembleDebug
   ```
   APK akan tersedia di: `app/build/outputs/apk/debug/`

---

## 🎯 Cara Penggunaan

### 📝 Quest Mode
1. **Tambah Quest Baru**
   - Masukkan nama quest di text field
   - Tekan tombol `+` untuk menambahkan
   - Quest muncul dalam bentuk kartu yang menarik

2. **Selesaikan Quest**
   - Tekan tombol **✓ Check** pada quest
   - Dapatkan **+100 EXP** untuk progress karakter
   - Quest akan hilang dari daftar

3. **Hapus Quest**
   - Tekan tombol **🗑️ Delete** jika quest tidak relevan
   - Tetap dapatkan **+100 EXP** sebagai bonus

### ⚔️ Timer Mode (Combat Arena)
1. **Pilih Monster**
   - Klik dropdown di atas gambar monster
   - Pilih musuh yang ingin dilawan
   - HP monster akan direset

2. **Mulai Pertempuran**
   - Tekan tombol **⚔️ Fight** untuk menyerang
   - Damage akan otomatis terjadi per detik
   - Stamina akan berkurang seiring waktu

3. **Kelola Stamina**
   - Monitor bar stamina di bagian atas
   - Tekan **💤 Rest** untuk memulihkan stamina
   - Tekan **⏸️ Pause** untuk menghentikan serangan sementara

4. **Kalahkan Monster**
   - Kurangi HP monster hingga 0
   - Dapatkan EXP reward otomatis
   - Monster respawn untuk pertempuran berikutnya

5. **Level Up!**
   - EXP bar terisi penuh = Level Up! 🎉
   - Stats meningkat otomatis
   - Stamina terisi penuh

---

## 🛠️ Tech Stack

### 🏗️ Architecture & Frameworks
- **Kotlin** - Modern programming language untuk Android
- **Jetpack Compose** - Declarative UI toolkit
- **MVVM Pattern** - Clean architecture dengan ViewModel
- **Coroutines & Flow** - Asynchronous programming

### 📚 Libraries & Dependencies
```kotlin
// UI & Design
- Jetpack Compose Material3
- Accompanist Drawable Painter
- Coil (Image Loading & GIF support)

// Data & State Management
- DataStore Preferences (Persistent storage)
- Gson (JSON serialization)
- Lifecycle ViewModel Compose

// Navigation
- Navigation Compose 2.9.5
```

### 🗂️ Project Structure
```
📦 com.azwin.dotask
 ┣ 📂 Model
 ┃ ┣ 📂 Fight
 ┃ ┃ ┣ 📂 Statistic (PlayerData, MonsterData)
 ┃ ┃ ┗ TimerData
 ┃ ┗ 📂 Quest (QuestData, ToDo)
 ┣ 📂 View
 ┃ ┣ 📂 Components (GameButton, StatisticBar)
 ┃ ┣ QuestView
 ┃ ┗ TimerView
 ┣ 📂 ViewModel
 ┃ ┣ 📂 Fight (TimerViewModel)
 ┃ ┗ Quest (QuestViewModel)
 ┣ 📂 Data
 ┃ ┣ QuestRepository
 ┃ ┗ SettingsManager
 ┗ MainActivity.kt
```

---

## 📸 Screenshots

> *Coming soon! Update dengan screenshot aplikasi Anda*

---

## 🎨 Design Features

### 🖼️ Visual Elements
- ✨ **Custom Pixel Art** - Monsters dan UI elements bergaya retro
- 🎭 **Themed Backgrounds** - Arena pertempuran yang dinamis
- 📊 **Animated Progress Bars** - Visual feedback yang jelas
- 🎪 **Hit Effects** - Animasi serangan saat combat

### 🎯 UX Highlights
- 🔄 **Swipe Navigation** - Horizontal pager untuk berpindah mode
- 📱 **Always-On Screen** - Layar tetap menyala saat combat
- 💾 **Auto-Save** - Tidak ada tombol save, semua otomatis
- 🎮 **Game-like Controls** - Button dengan feedback visual

---

## 🔮 Roadmap & Future Features

- [ ] 🏅 **Achievement System** - Unlock badges dan rewards
- [ ] 🎨 **Character Customization** - Pilih avatar dan equipment
- [ ] 📊 **Statistics Dashboard** - Lihat progress dan analytics
- [ ] 🌐 **Cloud Sync** - Backup progress ke cloud
- [ ] 🎵 **Sound Effects** - Audio feedback untuk actions
- [ ] 🌙 **Dark Mode** - Theme customization
- [ ] 🏆 **Leaderboard** - Compete dengan players lain
- [ ] 🗓️ **Daily Quests** - Quest harian dengan bonus reward

---

## 🤝 Kontribusi

Contributions, issues, dan feature requests sangat diterima! 🎉

1. Fork project ini
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

---

## 👨‍💻 Developer

**Azwin RX**
- GitHub: [@azwinrx](https://github.com/azwinrx)
- Repository: [Task-Hero](https://github.com/azwinrx/Task-Hero)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- 🤖 **Gemini AI** - Untuk bantuan development dan debugging
- 🎨 **Fantasy Game Assets** - Inspirasi untuk UI design
- 💪 **Jetpack Compose Community** - Untuk resources dan tutorials
- ⭐ **All Contributors** - Yang telah membantu project ini

---

<div align="center">

### ⭐ Jika project ini membantu, beri Star! ⭐

**Made with ❤️ and ☕ by Azwin RX**

*Transform Your Productivity into an Adventure!* 🚀

[⬆ Back to Top](#️-productive-heroes-️)

</div>
