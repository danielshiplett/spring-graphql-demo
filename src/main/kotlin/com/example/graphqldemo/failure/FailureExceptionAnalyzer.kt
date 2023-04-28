package com.example.graphqldemo.failure

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer
import org.springframework.boot.diagnostics.FailureAnalysis

class FailureExceptionAnalyzer : AbstractFailureAnalyzer<FailureException>() {
    override fun analyze(rootFailure: Throwable, cause: FailureException): FailureAnalysis {
        return FailureAnalysis(cause.getDescription(), cause.getAction(), cause)
    }
}