# Spring GraphQL Demo

An example project showing how to tie together Spring, Spring GraphQL, Kotlin, and Kobylynskyi GraphQL
Codegen together.

## Status

* Functioning code generation
  * Generates types and resolver interfaces
  * Demonstrates how to exclude nested types to support Kotlin immutability
* Spring GraphQL 1.2.0-SNAPSHOT tested
  * Confirmed INFO report at startup for missing resolvers
  * Caught my missing @Controller
  * Still can't help with missing type resolvers
  * Still can't fail the application startup