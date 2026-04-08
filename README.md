# Casio FX-500 Calculator Android App

Ứng dụng máy tính Casio FX-500 cho Android, được xây dựng bằng Kotlin và Android SDK.

## Tính năng

- ✅ Các phép tính cơ bản: cộng, trừ, nhân, chia
- ✅ Căn bậc hai (√) và phần trăm (%)
- ✅ Chức năng bộ nhớ: MRC, M+, M-
- ✅ Đổi dấu (+/-)
- ✅ Xóa (C) và Xóa tất cả (AC)
- ✅ Giao diện giống Casio FX-500MS thật

## Cấu trúc Project

```
casio-calculator-android/
├── app/
│   ├── src/main/java/com/casio/calculator/
│   │   └── MainActivity.kt       # Logic chính
│   ├── src/main/res/layout/
│   │   └── activity_main.xml     # Giao diện
│   ├── src/main/res/values/
│   │   ├── colors.xml           # Màu sắc Casio
│   │   ├── strings.xml          # Strings
│   │   └── themes.xml           # Styles
│   └── build.gradle
├── .github/workflows/
│   └── build.yml                # GitHub Actions
├── build.gradle
├── settings.gradle
└── README.md
```

## Build Local

```bash
# Clone repository
git clone https://github.com/yourusername/casio-calculator-android.git
cd casio-calculator-android

# Build Debug APK
./gradlew assembleDebug

# Build Release APK
./gradlew assembleRelease
```

APK sẽ được tạo tại:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release-unsigned.apk`

## GitHub Actions

Workflow tự động build APK khi:
- Push code lên branch `main` hoặc `master`
- Tạo Pull Request
- Chạy thủ công (workflow_dispatch)

Artifacts và Releases sẽ được tạo tự động.

## Yêu cầu

- Android SDK 34
- Kotlin 1.9.0
- Gradle 8.5
- JDK 17

## Giấy phép

MIT License
