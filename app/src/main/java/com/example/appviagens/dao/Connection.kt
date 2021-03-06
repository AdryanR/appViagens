package com.example.appviagens.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appviagens.model.*

@Database(entities = arrayOf(Pessoa::class, Viagem::class, TipoViagem::class, Despesa::class, CategoriaDespesa::class), version = 5)
abstract class Connection : RoomDatabase() {

    abstract fun pessoaDao(): PessoaDao
    abstract fun viagemDao(): ViagemDao
    abstract fun tipoViagemDao(): TipoViagemDao
    abstract fun DespesaDao(): DespesaDao
    abstract fun CategoriaDao(): CategoriaDao

    // Desing Pattern - Singleton
    companion object {
        var connection: Connection? = null

        fun getDB(context: Context): Connection {
            val temp = connection
            if (temp != null) {
                return temp
            } else {
                // conectar o banco
                val instance = Room.databaseBuilder(
                    context,
                    Connection::class.java,
                    "dbAppViagenss"
                ).build()
                connection = instance
                return instance
            }
        }

    }
}