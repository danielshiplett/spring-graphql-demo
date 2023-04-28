package com.example.graphqldemo.config

import com.example.graphqldemo.failure.FailureException
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.GraphQlSource.SchemaResourceBuilder
import org.springframework.graphql.execution.SchemaReport

@Configuration
class GraphQlConfig {
    @Bean
    fun sourceBuilderCustomizer(): GraphQlSourceBuilderCustomizer {
        return GraphQlSourceBuilderCustomizer { builder: SchemaResourceBuilder ->
            builder.inspectSchemaMappings { schemaReport ->
                if(schemaReport.unmappedFields().size > 0 || schemaReport.unmappedRegistrations().isNotEmpty()) {
                    throw GraphQlSchemaFailureException(schemaReport)
                }
            }
        }
    }

    class GraphQlSchemaFailureException(
        private val schemaReport: SchemaReport
    ): FailureException() {
        override fun getDescription(): String {
            return "Invalid schema mappings.  SchemaReport:\n\n$schemaReport\n"
        }

        override fun getAction(): String {
            return "See description for details of invalid schema mappings."
        }
    }
}