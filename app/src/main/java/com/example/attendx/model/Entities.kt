package com.example.attendx.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val middleName: String,
    val surName: String
){
    constructor(firstName: String,middleName: String,surName: String):this(Int.MIN_VALUE,firstName,middleName,surName)
}


@Entity(tableName = "organization")
data class Organization(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String?,
    val geofence: String
){
    constructor(name: String,address: String?,geofence: String) : this(Int.MIN_VALUE,name,address,geofence)
}


@Entity(tableName = "user_organization", primaryKeys = ["userId", "organizationId"],
    foreignKeys = [ForeignKey(entity = User::class, parentColumns =["id"], childColumns = ["userId"]),
    ForeignKey(entity = Organization::class, parentColumns =["id"], childColumns = ["organizationId"])],
    indices = [
        Index("userId"),
        Index("organizationId")
    ])
data class UserOrganization(
    val userId: Int,
    val organizationId: Int,
    val isAdmin: Boolean,
    val dateJoined: String
)