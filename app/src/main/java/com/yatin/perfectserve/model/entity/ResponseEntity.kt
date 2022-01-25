package com.yatin.perfectserve.model.entity

data class ResponseEntity(private val total: Int, val businesses: List<BusinessEntity>) {
}