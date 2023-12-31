package com.mrajaariziq.grandlibrary.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity (tableName = "TB_PINJAM")
data class DataPinjam(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NIS")
    val nisPinjam: Int,
    @ColumnInfo(name = "Nama")
    val namaPinjam: String,
    @ColumnInfo(name = "Judul")
    val judul: String,
    @ColumnInfo(name = "TanggalPinjam")
    val tglPinjam: String,
    @ColumnInfo(name = "TanggalPengembalian")
    val bataswaktu: String

)
