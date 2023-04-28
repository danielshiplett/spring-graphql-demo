package com.example.graphqldemo.failure

abstract class FailureException(message: String, cause: Throwable) : Exception(message, cause) {

    abstract fun getDescription(): String
    abstract fun getAction(): String
}