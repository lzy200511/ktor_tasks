// Task 4.1: Hello World server

import io.ktor.server.application.Application

fun Application.module() {
    configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
