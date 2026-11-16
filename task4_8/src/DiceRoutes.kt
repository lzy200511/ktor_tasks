// Task 4.8: request handlers

import io.ktor.server.pebble.*
import io.ktor.server.routing.*

suspend fun RoutingCall.diceForm() {
    respondTemplate("form.peb", emptyMap())
}

suspend fun RoutingCall.rollResult() {
    val roll = DiceRoll(numDice, dieSides)
    val context = mapOf("roll" to roll, "reroll_uri" to uri)
    respondTemplate("result.peb", context)
}
