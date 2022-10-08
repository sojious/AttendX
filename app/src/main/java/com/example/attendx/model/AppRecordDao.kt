package com.example.attendx.model

import androidx.room.*

@Dao
interface AppRecordDao {

    //Use queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Delete
    fun deleteUser(user: User)

    @Delete
    suspend fun deleteUsers(users: List<User>)

    @Query("SELECT * FROM user WHERE User.id=:id")
    suspend fun getUser(id: Int):User


    //Organization queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganization(organization: Organization)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganizations(organizations: List<Organization>)

    @Delete
    suspend fun deleteOrganization(organization: Organization)

    @Delete
    suspend fun deleteOrganizations(organizations: List<Organization>)



    //User_Organization pair queries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAndOrganization(instance: UserOrganization)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersAndOrganizations(instances: List<UserOrganization>)

    @Delete
    suspend fun deleteUserAndOrganization(instance: UserOrganization)

    @Delete
    suspend fun deleteUsersAndOrganizations(instances: List<UserOrganization>)


}
