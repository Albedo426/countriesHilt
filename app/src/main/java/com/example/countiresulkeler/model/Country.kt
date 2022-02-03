package com.example.countiresulkeler.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity//tablo adını () ekleyip içine tabloname şeklnde ekleyebilirim aksi taktirde Country
data class Country(


    @ColumnInfo(name = "capital")//eğer yazmaz isem valın yanında yazanı defauld alır
    @SerializedName("capital")
    val countryCapital: String,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val countryImgUrl: String,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String
){
    //burda kullanmamın sebebi  normalde gelen veride id yok sqlitte olmalı o yüzden böyle kullanıyorum harici bir şekilde ekliyorum
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0

}