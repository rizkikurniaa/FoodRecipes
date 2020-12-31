package com.kikulabs.foodrecipes.model

import android.os.Parcel
import android.os.Parcelable

class DataRecipes (
    var idMeal: String? = null,
    var strMeal: String? = null,
    var strCategory: String? = null,
    var strArea: String? = null,
    var strInstructions: String? = null,
    var strMealThumb: String? = null,
    var strYoutube: String? = null,
    var strIngredient: String? = null,
    var strMeasure: String? = null,
    var strSource: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(strMeal)
        parcel.writeString(strCategory)
        parcel.writeString(strArea)
        parcel.writeString(strInstructions)
        parcel.writeString(strMealThumb)
        parcel.writeString(strYoutube)
        parcel.writeString(strIngredient)
        parcel.writeString(strMeasure)
        parcel.writeString(strSource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataRecipes> {
        override fun createFromParcel(parcel: Parcel): DataRecipes {
            return DataRecipes(parcel)
        }

        override fun newArray(size: Int): Array<DataRecipes?> {
            return arrayOfNulls(size)
        }
    }
}