// Task 4.7: form and path parameters

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.pebble.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondTemplate("form.peb", emptyMap())
        }

        post("/roll") {
            val params = call.receiveParameters()
            val numDice = (params["num_dice"] ?: "1").toIntOrNull()
            val dieSides = (params["die_sides"] ?: "6").toIntOrNull()
            if (numDice == null || numDice <= 0 || dieSides == null || dieSides !in DiceRoll.VALID_DIE_SIDES) {
                call.respondText("Invalid dice selection", status = HttpStatusCode.BadRequest)
                return@post
            }
            val roll = DiceRoll(numDice, dieSides)
            call.respondTemplate("result.peb", mapOf("roll" to roll))
        }

        get(Regex("""/roll/(?<numDice>\d+)d(?<dieSides>\d+)""")) {
            val numDice = call.parameters["numDice"]?.toIntOrNull()
            val dieSides = call.parameters["dieSides"]?.toIntOrNull()
            if (numDice == null || numDice <= 0 || dieSides == null || dieSides !in DiceRoll.VALID_DIE_SIDES) {
                call.respondText("Invalid dice selection", status = HttpStatusCode.BadRequest)
                return@get
            }
            val roll = DiceRoll(numDice, dieSides)
            call.respondTemplate("result.peb", mapOf("roll" to roll))
        }
    }
}
