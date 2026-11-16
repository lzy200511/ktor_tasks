# Task 4 completion

Source: [Ktor Essentials](https://comp2850.github.io/ktor-guide/ktor).
Each task is a separate application and uses port 8080; run one at a time.

| Task | Implementation | Run from its directory |
| --- | --- | --- |
| 4.1 | Kotlin Toolchain Hello World server | `./run` |
| 4.2 | Gradle Kotlin DSL project with Netty and YAML configuration | `./gradlew run` |
| 4.3 | Serves the Task 2.3 Pico page at `/` and a linked second page at `/about.html` | `./run` |
| 4.4 | Serializable six-sided die roll returned as JSON at `/` | `./run` |
| 4.6.1 | Pebble die roll at `/`, JSON at `/json` | `./run` |
| 4.6.2 | Existing solution: shared base template | `./run` |
| 4.7 | POST form at `/roll`; GET path parameters such as `/roll/3d8` | `./run` |
| 4.8 | RoutingCall extension functions in separate files; existing GET behaviour retained | `./run` |
| 4.9 | Existing solution: custom 404 and 500 pages with matching HTTP status codes | `./run` |

Task 4.7 removes the Repeat link as required by the guide. Invalid numeric
selections return HTTP 400; paths not matching the roll pattern return 404.

## Scope and environment

The previously completed work on branch `work`, including Task 4.5.1 tests,
is preserved. Task 4.5.2 and all test execution are skipped as requested.
No build or runtime verification has been performed for these changes.

The Toolchain distribution download for `./kotlin init ktor-server` and access
to `start.ktor.io` were denied by the environment's network policy. Task 4.1
therefore uses the repository's existing Toolchain project structure. Task 4.2
is a locally written equivalent of the generator's basic Gradle/Netty/YAML
project, not a downloaded generator archive. It pins Kotlin 2.2.20, Ktor 3.3.1,
and Gradle 8.14.3, and requires JDK 21. No optional plugins are selected.
