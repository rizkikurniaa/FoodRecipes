package com.kikulabs.foodrecipes.model

import android.os.Parcel
import android.os.Parcelable

data class DataCategories (
    var strCategory: String? = null,
    var strCategoryThumb: String? = null,
    var strCategoryDescription: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(strCategory)
        parcel.writeString(strCategoryThumb)
        parcel.writeString(strCategoryDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataCategories> {
        override fun createFromParcel(parcel: Parcel): DataCategories {
            return DataCategories(parcel)
        }

        override fun newArray(size: Int): Array<DataCategories?> {
            return arrayOfNulls(size)
        }
    }
}