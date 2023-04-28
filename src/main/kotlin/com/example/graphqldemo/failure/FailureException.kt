package com.example.graphqldemo.failure

abstract class FailureException() : Exception("See description", null) {

    abstract fun getDescription(): String
    abstract fun getAction(): String
}