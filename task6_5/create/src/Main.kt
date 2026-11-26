// Task 6.5: populating database tables using Exposed's SQL DSL

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    transaction(MusicDatabase.db) {
        addLogger(StdOutSqlLogger)
        // Drop the child table first because it references the artist table.
        SchemaUtils.drop(AlbumTable, ArtistTable)
        SchemaUtils.create(ArtistTable, AlbumTable)

        val beatles = ArtistTable.insertAndGetId {
            it[artistName] = "The Beatles"
            it[info] = "https://www.thebeatles.com/"
        }
        val radiohead = ArtistTable.insertAndGetId {
            it[artistName] = "Radiohead"
            it[info] = "https://www.radiohead.com/"
        }
        val taylorSwift = ArtistTable.insertAndGetId {
            it[artistName] = "Swift, Taylor"
            it[isSolo] = true
            it[info] = "https://www.taylorswift.com/"
        }

        AlbumTable.insert {
            it[artist] = beatles
            it[title] = "Rubber Soul"
            it[year] = 1965
        }
        AlbumTable.insert {
            it[artist] = beatles
            it[title] = "Abbey Road"
            it[year] = 1969
        }
        AlbumTable.insert {
            it[artist] = radiohead
            it[title] = "The Bends"
            it[year] = 1995
        }
        AlbumTable.insert {
            it[artist] = radiohead
            it[title] = "OK Computer"
            it[year] = 1997
        }
        AlbumTable.insert {
            it[artist] = taylorSwift
            it[title] = "1989"
            it[year] = 2014
        }
        AlbumTable.insert {
            it[artist] = taylorSwift
            it[title] = "Folklore"
            it[year] = 2020
        }
    }
}
