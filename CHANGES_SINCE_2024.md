# Changes Since v1.29 (Late 2024)
*For standard/non-Pro usage*

While the project has introduced a subscription system for advanced AI features, the core application has received several maintenance, user experience, and technical updates that benefit all users.

---

## 🌍 1. Translations & Localization
The app is now fully accessible to more users worldwide with new translation updates for:
-   **Hindi**
-   **Indonesian**
-   **Spanish**
-   **Catalan**
-   And other language string updates.

## 📱 2. Android 13+ Support
-   **Notification Permission**: Added explicit support for **runtime notification permissions**, resolving issues where the app might fail to detect or reply to messages on Android 13 and above without manual setup.

## 🔐 3. Modernized Sign-In
-   **Google Sign-In**: Upgraded to use the new Android **Credential Manager** for smoother single-tap login.
-   **Email Links**: Added support for passwordless sign-in via links.

## 🛠️ 4. Better User Experience (UX)
-   **Unsaved Changes Alerts**: Added warnings when you try to leave editors (like custom reply settings) without saving, preventing accidental loss of your setup.
-   **Crash & Infinite Loop Fixes**: Resolved bugs that could cause pages to refresh infinitely or simulated fields to update incorrectly.

## 🏷️ 5. Rebranding
-   The app is slowly transitioning to the name **Atomatic** (visible on the Play Store, and parts of the UI), but remains fully backwards compatible.

## 🧱 6. Behind the Scenes (Stability)
-   **Modern Tech Stack**: Upgraded building tools (Gradle 8.x) and Kotlin versioning.
-   **Massive Testing Overhaul**: Developers added hundreds of new tests expanding coverage to ~95%, guaranteeing that future updates don't accidentally break core auto-replying features.

---
**Summary**: If you don't use AI or Subscriptions, this period was safely focused on translations, critical Android 13+ notification permissions, login reliability, and general robustness.
