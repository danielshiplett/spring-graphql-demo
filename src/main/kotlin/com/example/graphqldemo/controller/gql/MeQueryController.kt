package com.example.graphqldemo.controller.gql

import com.example.graphql.api.MeQueryResolver
import com.example.graphql.model.CommentGQL
import com.example.graphql.model.UserGQL
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class MeQueryController: MeQueryResolver {

    @QueryMapping
    override fun me(): UserGQL {
        return UserGQL("testuser", "Test User")
    }

    @SchemaMapping(typeName = "User", field = "comments")
    fun comments(user: UserGQL): List<CommentGQL> {
        return listOf(
            CommentGQL("comment-1", "some text", user),
            CommentGQL("comment-2", "another comment", user)
        )
    }
}