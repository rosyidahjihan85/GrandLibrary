package com.mrajaariziq.grandlibrary.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "TB_BUKU")
data class DataBuku(
    @PrimaryKey
    @ColumnInfo (name = "ID")
    val idBuku :Int,
    @ColumnInfo (name = "JudulBk")
    val judulBk : String,
    @ColumnInfo (name = "Pengarang")
    val pengarangBuku :String,
    @ColumnInfo (name = "Penerbit")
    val penerbitBuku :String,

)
