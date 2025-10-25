# Cake Delivery App (Jetpack Compose)

**Tech:** Kotlin · Jetpack Compose · Room · Datastore · Jetpack Navigation

## Overview
A demo Cake Delivery app built to practice modern Android development using Jetpack Compose. The app uses a fake API (local JSON / mocked responses) for product data, persists saved cakes to **Room**, and stores onboarding & theme preferences using **Datastore**. Includes light/dark theme support and navigation between screens.

## Key Features
- Browse cake list fetched from a fake API (local JSON).
- Save/unsave cakes using **Room** (local persistence).
- Onboarding flow controlled with **Datastore** (shows only once).
- Theme support (light & dark); user preference stored with Datastore.
- Navigation implemented using **Jetpack Navigation for Compose**.

## Architecture / Tools
- Kotlin + Jetpack Compose
- Room for local database
- DataStore (Proto or Preferences) for simple key-value storage (onboarding, theme)
- Fake API via local JSON file / repository pattern (no backend)
- Android Studio | Gradle | Kotlin Coroutines

## How to run
1. Clone the repo  
   `git clone https://github.com/yourusername/cake-delivery-app.git`
2. Open in Android Studio
3. Run on emulator or device (min SDK: specify yours)
4. (Optional) If using local JSON as fake API, no extra setup required.

## What to include in the repo
- `app/src/main/java/...` (source code)
- `app/src/main/res/` (screenshots in `drawable` if any)
- `app/src/main/assets/cakes.json` (fake API data) or sample network mock
- `app/src/main/java/.../data/` (Room entities, DAO, database)
- `README.md` (this file)
- `screenshots/` folder with preview images
- `app-release.apk` or instructions to build (optional)

## Notes
This project demonstrates ability to implement local persistence, user preferences, navigation, and theming in Compose without requiring a live backend. Great for interviews and demoing on GitHub.

