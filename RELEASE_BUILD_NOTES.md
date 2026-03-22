# Application Release & Build Notes

This document summarizes the current status of the application releases and guidelines for building it yourself.

## 1. Release Status on GitHub
-   **Last Tagged Release**: **v1.29** (September 25, 2024).
-   **Current Status**: The project is **highly active** with recent code merges in **March 2026**.
-   **Distribution Focus**: Recent updates are pushed directly to Direct stores (e.g., Google Play Store list under **Atomatic** as of Dec 2025). The developers are not currently creating formal GitHub releases with tagged APKs for every build cycle.

## 2. Key Changes Since 2024
-   **Rebranding**: Transitioning to the name **Atomatic** for some channels.
-   **New Features**: b
    *   **Passwordless Login**: Added via Firebase.
    *   **New Activities**: e.g., `SubscriptionInfoActivity`.
-   **Technical**: Upgrades to **JDK 21** and massive **Test Coverage Expansion** in 2026.

## 4. Why Login? (Purpose)
In the **Google Play** version, login is used to:
-   **Verify Subscriptions**: Link your Google account to premium "Pro" features (like server-side AI).
-   **Sync Devices**: Manage devices linked to your paid account.

### 🚫 No Login in your Custom Build!
Because you are building the **`Default`** flavor for your own fork:
1.  There is **no login screen** inside the app.
2.  The `Default` flavor is fully **local/offline-first** by design.
3.  You will not be prompted to log in at all, and it is completely **free and safe** from syncing data to third-party endpoints.

---

## 3. Building From Source (Usage & Costs)

### 🟢 Do you need to pay?
**No.** The app is licensed under the **GNU General Public License (GPL)**. You can build, modify, and run the code for free.

### 🛡️ Paid/Pro Features
The app contains "Pro" features (such as **Automatic AI replies**).
-   **Google Play Flavor**: Uses Google Play Billing.
-   **Default Flavor (F-Droid/Raw)**: Explicitly disables Pro features by returning `false` for access checks.

### 🔓 Unlocking Features for Personal Use
Because you possess the source code, you can unlock all features by modifying the code directly in your fork:

1.  Open the file:
    `app/src/Default/java/com/parishod/watomatic/model/subscription/SubscriptionManagerImpl.kt`
2.  Update the return values to `true`:
    ```kotlin
    override fun isProUser(): Boolean = true
    override fun hasFeatureAccess(feature: SubscriptionManager.ProFeature): Boolean = true
    ```
3.  Build the **`Default`** flavor APK.

> [!CAUTION]
> **Automatic AI Response Limitations**
> Simply changing the local code to `true` **will not make Automatic AI work**. The app sends a request to a backend server (`AtomaticAIService`) that verifies your account status. The server will reject requests from non-paying users.
> 
> **The Free Alternative**: Use **BYOK (Bring Your Own Key)** mode!
> -   Go to settings and configure your own **OpenAI**, **Claude**, or **Gemini** API Key.
> -   This mode runs purely from your device and **does not require a subscription** or backend verification. It is completely free to use from your own build!
> 
> > [!TIP]
> > **API Keys Can Be Free!**
> > You don't necessarily have to pay to use your own API key.
> > -   **Google AI Studio (Gemini)**: Offers a **free tier** for personal use.
> > -   **Groq** / other providers: Often have free rate-limited tiers.
> > You can generate a key online for free, paste it into the app, and use AI features cost-free!
