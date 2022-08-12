package com.android.games.domain

import java.lang.reflect.GenericArrayType

data class Games(
    val count: Int = -1,
    val description: String = "",
    val filters: Filters = Filters(),
    val next: String = "",
    val nofollow: Boolean = false,
    val nofollow_collections: List<String> = mutableListOf(),
    val noindex: Boolean = false,
    val previous: Any? = null,
    val results: MutableList<Result> = mutableListOf(),
    val seo_description: String = "",
    val seo_h1: String = "",
    val seo_keywords: String = "",
    val seo_title: String = ""
)

data class Filters(
    val years: List<Year> = mutableListOf()
)

data class Year(
    val count: Int,
    val decade: Int,
    val filter: String,
    val from: Int,
    val nofollow: Boolean,
    val to: Int,
    val years: List<YearX>
)

data class YearX(
    val count: Int,
    val nofollow: Boolean,
    val year: Int
)