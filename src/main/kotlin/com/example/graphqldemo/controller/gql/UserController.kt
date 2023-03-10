package com.example.graphqldemo.controller.gql

import com.example.graphql.api.MeQueryResolver
import com.example.graphql.model.Comment
import com.example.graphql.model.User
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserController: MeQueryResolver {

    @QueryMapping
    override fun me(): User {
        return User("testuser", "Test User")
    }

    // @SchemaMapping must be on the method.  As long as the method name matches a field name in the type this will
    // work without any more annotations.  The injected field is the parent type that has the field.  So, in this case,
    // User.comments is resolved.
    @SchemaMapping
    fun comments(user: User): List<Comment> {
        return listOf(
            Comment("comment-1", "some text", user),
            Comment("comment-2", "another comment", user)
        )
    }
}