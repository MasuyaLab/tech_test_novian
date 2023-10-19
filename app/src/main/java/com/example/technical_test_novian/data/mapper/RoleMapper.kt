package com.example.technical_test_novian.data.mapper

import com.example.technical_test_novian.data.model.RolesDto
import com.example.technical_test_novian.domain.model.Roles

fun RolesDto.toDomain(): Roles {
    return Roles(
        kdRole = this.kdRoles.toInt(),
        nmRole = this.nmRole
    )
}