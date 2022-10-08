package com.example.attendx.model

class AppRepository(val dao:AppRecordDao) {

    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }

    suspend fun insertOrganization(organization:Organization){
        dao.insertOrganization(organization)
    }
}