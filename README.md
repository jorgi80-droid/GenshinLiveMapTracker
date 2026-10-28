# Genshin Live Map Tracker

Android companion app for tracking map collectibles and personal collection progress.

## Build APK with GitHub Actions

The workflow at `.github/workflows/android-apk.yml` builds a debug APK when source code is pushed to `main`, or can be run manually from **Actions → Android APK Build → Run workflow**.

## Source files required

Upload the contents of `GenshinLiveMapTracker-GitHubBuild.zip` (extract the ZIP first; upload its contents, not the ZIP itself) to the repository root. Preserve directories such as `app/`, `gradle/`, and `.github/workflows/`. If GitHub asks whether to replace `.github/workflows/android-apk.yml`, keep the workflow already in this repository or use the matching version from the ZIP.

After committing the source files, open **Actions**, select **Android APK Build**, and wait for the run to finish. If successful, download `GenshinLiveMapTracker-debug-apk` from the run's **Artifacts** section.

## Important limitations

- A successful workflow run is required before an APK exists.
- The debug APK is for testing, not a signed production release.
- Actual world-map coverage depends on the dataset provided; sample data is not a complete authoritative map.
- Player position tracking does not read or modify the game's process. Automatic in-game position is not guaranteed.
