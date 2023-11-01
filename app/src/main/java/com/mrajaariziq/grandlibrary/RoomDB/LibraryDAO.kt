package com.mrajaariziq.grandlibrary.RoomDB

import androidx.room.*

@Dao
interface LibraryDAO {
@Insert
fun insertDataBuku(dataBuku: DataBuku)
@Insert
fun insertDataPinjam(dataPinjam: DataPinjam)
@Update
fun updateDataBuku(dataBuku: DataBuku)
@Update
fun updateDataPinjam(dataPinjam: DataPinjam)
@Delete
fun deleteDataBuku(dataBuku: DataBuku)
@Delete
fun deleteDataPinjam(dataPinjam: DataPinjam)
@Query("SELECT * FROM TB_BUKU")
fun getAllBuku():List<DataBuku>
@Query("SELECT * FROM TB_PINJAM")
fun getAllPinjam():List<DataPinjam>
}