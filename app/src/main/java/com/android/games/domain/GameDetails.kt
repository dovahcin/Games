package com.android.games.domain

import java.util.concurrent.Flow


data class DetailsDataModel(
    val gameDetails: GameDetails = GameDetails(),
    val screenShots: ScreenShots = ScreenShots()
)

data class GameDetails(
    val achievements_count: Int = -1,
    val added: Int = -1,
    val added_by_status: AddedByStatus = AddedByStatus(),
    val additions_count: Int = -1,
    val alternative_names: List<Any> = mutableListOf(),
    val background_image: String = "",
    val background_image_additional: String = "",
    val clip: Any? = null,
    val creators_count: Int = -1 ,
    val description: String = "",
    val description_raw: String = "",
    val developers: MutableList<Developer> = mutableListOf(),
    val dominant_color: String = "",
    val esrb_rating: EsrbRating? = EsrbRating(),
    val game_series_count: Int = -1,
    val genres: List<Genre> = mutableListOf(),
    val id: Int = -1,
    val metacritic: Int = -1,
    val metacritic_platforms: List<MetacriticPlatform> = mutableListOf(),
    val metacritic_url: String = "",
    val movies_count: Int = -1,
    val name: String = "",
    val name_original: String = "",
    val parent_achievements_count: Int = -1,
    val parent_platforms: List<ParentPlatform> = mutableListOf(),
    val parents_count: Int = -1,
    val platforms: MutableList<PlatformX> = mutableListOf(),
    val playtime: Int = -1,
    val publishers: MutableList<Publisher> = mutableListOf(),
    val rating: Double = -1.0,
    val rating_top: Int = -1,
    val ratings: List<Rating> = mutableListOf(),
    val ratings_count: Int = -1,
    val reactions: Reactions = Reactions(),
    val reddit_count: Int = -1,
    val reddit_description: String = "",
    val reddit_logo: String = "",
    val reddit_name: String = "",
    val reddit_url: String = "",
    val released: String = "",
    val reviews_count: Int = -1,
    val reviews_text_count: Int = -1,
    val saturated_color: String = "",
    val screenshots_count: Int = -1,
    val slug: String = "",
    val stores: MutableList<Store> = mutableListOf(),
    val suggestions_count: Int = -1,
    val tags: MutableList<Tag> = mutableListOf(),
    val tba: Boolean = false,
    val twitch_count: Int = -1,
    val updated: String = "",
    val user_game: Any? = null ,
    val website: String = "",
    val youtube_count: Int = -1
){
    val ratedStar
        get() = rating.toFloat()
    val ratedText
    get() = "($rating/5)"

    val updatedDate
    get() = updated.take(10)
}


data class Developer(
    val games_count: Int ,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)


data class MetacriticPlatform(
    val metascore: Int,
    val platform: Platform,
    val url: String
)

data class Publisher(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)


data class Reactions(
    val `1`: Int = -1,
    val `10`: Int = -1,
    val `11`: Int = -1,
    val `12`: Int = -1,
    val `14`: Int = -1,
    val `15`: Int = -1,
    val `16`: Int = -1,
    val `2`: Int = -1,
    val `21`: Int = -1,
    val `3`: Int = -1,
    val `4`: Int = -1,
    val `5`: Int = -1,
    val `6`: Int = -1,
    val `7`: Int = -1
)


