package com.lt.ltotc.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by HankGreen on 2017/7/17.
 */
class DemoEntity {
    var nextPageToken: String? = null
    var prevPageToken: String? = null
    var requestCount = 0
    var responseCount = 0
    var totalResults = 0
    var items: List<ItemsEntity>? = null

    class ItemsEntity : Parcelable {
        var detail: String? = null
        var href: String? = null
        var id = 0
        var img: String? = null
        var name: String? = null
        var pubDate: String? = null
        var type = 0

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(detail)
            dest.writeString(href)
            dest.writeInt(id)
            dest.writeString(img)
            dest.writeString(name)
            dest.writeString(pubDate)
            dest.writeInt(type)
        }

        constructor() {}
        protected constructor(`in`: Parcel) {
            detail = `in`.readString()
            href = `in`.readString()
            id = `in`.readInt()
            img = `in`.readString()
            name = `in`.readString()
            pubDate = `in`.readString()
            type = `in`.readInt()
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<ItemsEntity?> = object : Parcelable.Creator<ItemsEntity?> {
                override fun createFromParcel(source: Parcel): ItemsEntity? {
                    return ItemsEntity(source)
                }

                override fun newArray(size: Int): Array<ItemsEntity?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}