// Task 6.4: creating database tables

import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    transaction(MusicDatabase.db) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(AlbumTable, ArtistTable)
        SchemaUtils.create(ArtistTable, AlbumTable)
    }
}
