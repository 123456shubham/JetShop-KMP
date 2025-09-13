package org.example.jetshop.model.productDetails

import kotlinx.serialization.Serializable
@Serializable
data class ReviewsData(
    var fiveStarReviewCount: Int?,
    var fourStarReviewCount: Int?,
    var oneStarReviewCount: Int?,
    var reviewList: List<Review >,
    var threeStarReviewCount: Int?,
    var totalReviewCount: Int?,
    var twoStarReviewCount: Int?
)