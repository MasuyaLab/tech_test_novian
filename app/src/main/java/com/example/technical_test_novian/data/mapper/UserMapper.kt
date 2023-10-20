package com.example.technical_test_novian.data.mapper

import com.example.technical_test_novian.data.model.UserDto
import com.example.technical_test_novian.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        uid = this.uid,
        uName = this.uName,
        kdRole = this.kdRole,
        role = "",
        uPw = uPw
    )
}