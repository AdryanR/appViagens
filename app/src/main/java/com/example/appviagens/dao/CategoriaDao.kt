package com.example.appviagens.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appviagens.model.CategoriaDespesa

@Dao
interface CategoriaDao {

    @Insert()
    suspend fun insert(categoria: CategoriaDespesa): Long

    //
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(categoria: CategoriaDespesa)

    @Query("select * from CategoriaDespesa order by nome")
    fun findAll(): LiveData<List<CategoriaDespesa>>

    @Query("select seq from sqlite_sequence where name='CategoriaDespesa'")
    fun getLastInsertID(): Int
    // select max(id) from CategoriaDespesa

    @Query("Delete from CategoriaDespesa where CategoriaDespesa.id = :id")
    suspend fun deleteByID(id : Int)
}