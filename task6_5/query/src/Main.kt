// Task 6.5: querying a database using Exposed's SQL DSL

import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main(args: Array<String>) {
    // An optional command line argument selects the exclusive year cutoff.
    val afterYear = if (args.isEmpty()) 2000 else args.singleOrNull()?.toIntOrNull()
    require(afterYear != null) { "Supply at most one integer year, for example 1990" }

    transaction(MusicDatabase.db) {
        addLogger(StdOutSqlLogger)
        println("Number of artists: ${ArtistTable.selectAll().count()}")
        println("Number of albums: ${AlbumTable.selectAll().count()}")

        println("Bands:")
        ArtistTable.selectAll()
            .where { ArtistTable.isSolo eq false }
            .orderBy(ArtistTable.artistName to SortOrder.ASC)
            .forEach { println(it[ArtistTable.artistName]) }

        println("Albums released after $afterYear:")
        (AlbumTable innerJoin ArtistTable)
            .select(ArtistTable.artistName, AlbumTable.title, AlbumTable.year)
            .where { AlbumTable.year greater afterYear }
            .orderBy(
                ArtistTable.artistName to SortOrder.ASC,
                AlbumTable.year to SortOrder.ASC
            )
            .forEach {
                println("${it[ArtistTable.artistName]} - ${it[AlbumTable.title]} (${it[AlbumTable.year]})")
            }
    }
}
