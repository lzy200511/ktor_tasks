# Task 6: Databases

Requirements: [database chapter](https://comp2850.github.io/ktor-guide/db).
Sections 6.1–6.3 explain JDBC, Exposed, and database connections; they have no
separately numbered exercises. Their connection pattern is used by the
existing database modules.

## Completed code

- **6.4:** retains the Exposed schema; drops the child table before its parent and logs SQL explicitly.
- **6.5:** creates three artists (two bands and one solo artist) and six albums released in different years. Queries print counts, alphabetically sorted band names, and joined album details strictly after a selected year, in `artist_name - album_title (year)` format. The default cutoff is 2000; the query program also accepts one integer command line argument.
- **6.6:** queries artists through the DAO API and sorts them by name. Queries albums through the DAO API and sorts by artist name, then release year. Pebble templates render an unordered artist list and a three-column album table, with empty-state messages, descriptive page titles, table caption and column headers, and links home. Rendering remains inside the transaction so entity relationships can be read safely.
- **6.7:** applies the same finished request handlers and templates to this project's application code. Existing test code is preserved; writing the additional test and running tests are skipped as requested.

## Create and use the database

Run commands from the indicated task directory. Creation resets that task's
existing artist/album data, as in the starter application.

From `task6_4`, create the empty schema:

```sh
./kotlin run
```

From `task6_5`, create the populated database:

```sh
./kotlin run -m create
./kotlin run -m query
```

The seed data gives **3 artists and 6 albums**. The band query returns
`Radiohead` and `The Beatles`. With the default cutoff, the album query returns:

```text
Swift, Taylor - 1989 (2014)
Swift, Taylor - Folklore (2020)
```

After the create process has stopped, run these commands from `task6_6`
(or from `task6_7` to use its application):

```sh
./prepare-database
./run
```

`prepare-database` copies `../task6_5/music.mv.db` into the current task,
and preserves an existing destination. It explains the required creation
command if the source is missing. The database must not be open in another
process during copying. Do not run both web servers on port 8080 together.

Visit `/`, `/artists`, and `/albums` on the server. The album order is:
Radiohead (1995, 1997), Swift, Taylor (2014, 2020), The Beatles (1965, 1969).

On Windows, use `kotlin.bat`, `prepare-database.bat`, and `run.bat`.
Generated H2 database and lock files are ignored by Git; the source code
recreates the sample data instead of committing a machine-specific database.

## Verification performed

The original `./kotlin build` could not download Kotlin Toolchain 0.12.2
(HTTP 403). A temporary Maven project outside the repository compiled the
schema creator, data creator, query program, and both servers successfully,
using JDK 21, Kotlin 2.3.20, Exposed 1.5.1, H2 2.5.252, Logback 1.5.38,
and Ktor 3.5.2 for server dependencies. The repository's build configuration
was not replaced. This verifies source compatibility with those explicit
versions, not the unavailable Toolchain's complete build.

The compiled schema and data creation programs were run successfully; the
compiled query program returned 3 artists, 6 albums, the two band names,
and the two post-2000 albums shown above. The resulting database was copied
into the local Task 6.6 and Task 6.7 directories with the provided scripts.
Generated database files are not part of the Git commit.

No test suite, browser tests, or additional test-writing exercises were run.
The web applications were compiled but not started or checked in a browser.
