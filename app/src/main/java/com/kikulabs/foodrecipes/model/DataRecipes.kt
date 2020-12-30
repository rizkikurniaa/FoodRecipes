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
    var strIngredient1: String? = null,
    var strIngredient2: String? = null,
    var strIngredient3: String? = null,
    var strIngredient4: String? = null,
    var strIngredient5: String? = null,
    var strIngredient6: String? = null,
    var strIngredient7: String? = null,
    var strIngredient8: String? = null,
    var strIngredient9: String? = null,
    var strIngredient10: String? = null,
    var strIngredient11: String? = null,
    var strIngredient12: String? = null,
    var strIngredient13: String? = null,
    var strIngredient14: String? =  null,
    var strIngredient15: String? = null,
    var strIngredient16: String? = null,
    var strIngredient17: String? = null,
    var strIngredient18: String? = null,
    var strIngredient19: String? = null,
    var strIngredient20: String? = null,
    var strMeasure1: String? = null,
    var strMeasure2: String? = null,
    var strMeasure3: String? = null,
    var strMeasure4: String? = null,
    var strMeasure5: String? = null,
    var strMeasure6: String? = null,
    var strMeasure7: String? = null,
    var strMeasure8: String? = null,
    var strMeasure9: String? = null,
    var strMeasure10: String? = null,
    var strMeasure11: String? = null,
    var strMeasure12: String? = null,
    var strMeasure13: String? = null,
    var strMeasure14: String? = null,
    var strMeasure15: String? = null,
    var strMeasure16: String? = null,
    var strMeasure17: String? = null,
    var strMeasure18: String? = null,
    var strMeasure19: String? = null,
    var strMeasure20: String? = null,
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
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(strIngredient1)
        parcel.writeString(strIngredient2)
        parcel.writeString(strIngredient3)
        parcel.writeString(strIngredient4)
        parcel.writeString(strIngredient5)
        parcel.writeString(strIngredient6)
        parcel.writeString(strIngredient7)
        parcel.writeString(strIngredient8)
        parcel.writeString(strIngredient9)
        parcel.writeString(strIngredient10)
        parcel.writeString(strIngredient11)
        parcel.writeString(strIngredient12)
        parcel.writeString(strIngredient13)
        parcel.writeString(strIngredient14)
        parcel.writeString(strIngredient15)
        parcel.writeString(strIngredient16)
        parcel.writeString(strIngredient17)
        parcel.writeString(strIngredient18)
        parcel.writeString(strIngredient19)
        parcel.writeString(strIngredient20)
        parcel.writeString(strMeasure1)
        parcel.writeString(strMeasure2)
        parcel.writeString(strMeasure3)
        parcel.writeString(strMeasure4)
        parcel.writeString(strMeasure5)
        parcel.writeString(strMeasure6)
        parcel.writeString(strMeasure7)
        parcel.writeString(strMeasure8)
        parcel.writeString(strMeasure9)
        parcel.writeString(strMeasure10)
        parcel.writeString(strMeasure11)
        parcel.writeString(strMeasure12)
        parcel.writeString(strMeasure13)
        parcel.writeString(strMeasure14)
        parcel.writeString(strMeasure15)
        parcel.writeString(strMeasure16)
        parcel.writeString(strMeasure17)
        parcel.writeString(strMeasure18)
        parcel.writeString(strMeasure19)
        parcel.writeString(strMeasure20)
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