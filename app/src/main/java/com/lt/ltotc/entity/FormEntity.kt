package com.lt.ltotc.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable

/**
 * Created by HankGreen on 2017/7/17.
 */
class FormEntity : BaseObservable, Parcelable {
    var id: String? = null
    var name: String? = null
    var sex: String? = null
    var bir: String? = null
    var hobby: String? = null
    var marry: Boolean? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(sex)
        dest.writeString(bir)
        dest.writeString(hobby)
        dest.writeValue(marry)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        name = `in`.readString()
        sex = `in`.readString()
        bir = `in`.readString()
        hobby = `in`.readString()
        marry = `in`.readValue(Boolean::class.java.classLoader) as Boolean
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<FormEntity?> = object : Parcelable.Creator<FormEntity?> {
            override fun createFromParcel(source: Parcel): FormEntity? {
                return FormEntity(source)
            }

            override fun newArray(size: Int): Array<FormEntity?> {
                return arrayOfNulls(size)
            }
        }
    }
}