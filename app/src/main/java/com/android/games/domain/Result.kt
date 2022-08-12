package com.android.games.domain

import android.media.Rating

data class Result(
    val added: Int,
    val added_by_status: AddedByStatus,
    val background_image: String?,
    val clip: Any,
    val dominant_color: String,
    val esrb_rating: EsrbRating,
    val genres: MutableList<Genre>,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val parent_platforms: List<ParentPlatform>,
    val platforms: List<PlatformX>,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val saturated_color: String,
    val short_screenshots: MutableList<ShortScreenshot>,
    val slug: String,
    val stores: List<Store>,
    val suggestions_count: Int,
    val tags: List<Tag>,
    val tba: Boolean,
    val updated: String,
    val user_game: Any
) {
    val gameRating
    get() = rating.toFloat()

}

data class AddedByStatus(
    val beaten: Int = -1,
    val dropped: Int = -1,
    val owned: Int = -1,
    val playing: Int = -1,
    val toplay: Int = -1,
    val yet: Int = -1
)

data class EsrbRating(
    val id: Int = -1,
    val name: String = "",
    val slug: String = ""
)

data class ParentPlatform(
    val platform: Platform
)
data class Platform(
    val id: Int = -1,
    val name: String = "",
    val slug: String = ""
)

data class PlatformX(
    val platform: PlatformXX,
    val released_at: String,
    val requirements_en: requirements_en,
    val requirements_ru: Any
)

data class requirements_en(
    val minimum: String,
    val recommended: String
)

data class ShortScreenshot(
    val id: Int,
    val image: String
)
data class PlatformXX(
    val games_count: Int,
    val id: Int,
    val image: Any,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Any,
    val year_start: Int
)

data class Store(
    val id: Int,
    val store: StoreX
)

data class StoreX(
    val domain: String,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)

data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String ,
    val language: String ,
    val name: String ,
    val slug: String
)
data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)

data class Genre(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)