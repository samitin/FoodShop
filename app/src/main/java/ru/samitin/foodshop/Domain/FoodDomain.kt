package ru.samitin.foodshop.Domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodDomain(
    var title :String="",
    var pic : String="",
    var description : String="",
    var free : Double=0.0,
    var numberInCart : Int=0
): Parcelable
