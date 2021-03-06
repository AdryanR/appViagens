package com.example.appviagens.repository

import android.app.Application
import com.example.appviagens.dao.Connection
import com.example.appviagens.dao.PessoaDao
import com.example.appviagens.model.Pessoa

class PessoaRepository(app: Application) {

    private val pessoaDao: PessoaDao

    init {
        pessoaDao = Connection
            .getDB(app).pessoaDao()
    }

    suspend fun save(pessoa: Pessoa) {
        if (pessoa.id == 0) {
            pessoaDao.insert(pessoa)
            // insere os tipos de viagens caso não tenha ainda, que são fixos
            pessoaDao.InsertTiposViagens()
        }
        else {
            pessoaDao.update(pessoa)
        }
    }

    suspend fun login(login: String, senha: String): Pessoa? = pessoaDao.login(login,senha)

//    suspend fun findAll(): List<Pessoa> = pessoaDao.findAll()
//
//    suspend fun findById(id: Int) = pessoaDao.findById(id)
//
//    suspend fun delete(contato: Pessoa) = pessoaDao.delete(contato)




}