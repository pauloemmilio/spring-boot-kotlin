package com.paulo.springkotlin.entities

import com.sun.istack.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
class Contact (
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @field:NotNull
        @field:Size(min = 5, max = 50, message = "Name invalid")
        var name: String,

        @field:NotNull
        @field:Email
        var email: String
)