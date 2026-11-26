// Task 6.7

import io.ktor.server.pebble.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

suspend fun RoutingCall.artistsPage() {
    suspendTransaction {
        val artists = Artist.all().sortedBy { it.name }
        respondTemplate("artists.peb", mapOf("artists" to artists))
    }
}
