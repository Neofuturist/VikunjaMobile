package ru.vikunja.mobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform