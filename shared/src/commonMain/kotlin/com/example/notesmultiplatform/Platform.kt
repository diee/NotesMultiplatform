package com.example.notesmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform