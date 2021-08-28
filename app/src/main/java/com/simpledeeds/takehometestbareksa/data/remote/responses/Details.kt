package com.simpledeeds.takehometestbareksa.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("category")
    val category: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("custody")
    val custody: String,
    @SerializedName("im_avatar")
    val imAvatar: String,
    @SerializedName("im_name")
    val imName: String,
    @SerializedName("inception_date")
    val inceptionDate: String,
    @SerializedName("min_balance")
    val minBalance: Int,
    @SerializedName("min_redemption")
    val minRedemption: Int,
    @SerializedName("min_subscription")
    val minSubscription: Int,
    @SerializedName("nav")
    val nav: Double,
    @SerializedName("return_cur_year")
    val returnCurYear: Double,
    @SerializedName("return_five_year")
    val returnFiveYear: Double,
    @SerializedName("return_four_year")
    val returnFourYear: Double,
    @SerializedName("return_inception_growth")
    val returnInceptionGrowth: Double,
    @SerializedName("return_one_day")
    val returnOneDay: Double,
    @SerializedName("return_one_month")
    val returnOneMonth: Double,
    @SerializedName("return_one_week")
    val returnOneWeek: Double,
    @SerializedName("return_one_year")
    val returnOneYear: Double,
    @SerializedName("return_six_month")
    val returnSixMonth: Double,
    @SerializedName("return_three_month")
    val returnThreeMonth: Double,
    @SerializedName("return_three_year")
    val returnThreeYear: Double,
    @SerializedName("return_two_year")
    val returnTwoYear: Double,
    @SerializedName("total_unit")
    val totalUnit: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("type_id")
    val typeId: Int
)