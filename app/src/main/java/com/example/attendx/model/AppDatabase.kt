package com.example.attendx.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [User::class,Organization::class,UserOrganization::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appRecordDao(): AppRecordDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_org_database"
                ).build()
                INSTANCE = database
                database
            }
        }
    }

    class AppDatabaseCallBack: RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database->

                GlobalScope.launch {
                    val org1=database.appRecordDao().insertOrganization(Organization(name = "Nestle", address = "8, industrial estate, Agbara", geofence = "24,35,44"))
                    val org2=database.appRecordDao().insertOrganization(Organization(name = "Flutteerwave", address = "16,Elemoro close, Lekki", geofence = "21,30,15"))
                    val org3=database.appRecordDao().insertOrganization(Organization(name = "Wisdom College", address = "3, Flowermill  Estate, Magbon", geofence = "24,35,44"))
                    val org4=database.appRecordDao().insertOrganization(Organization(name = "Darul-Kitaab Islamic institute", address = "8,Flourmill Estate, Magbon", geofence = "20,39,41"))

                    val user1=database.appRecordDao().insertUser(User(firstName = "Adesoji", middleName = "Yekeen", surName = "Olowa"))
                    val user2=database.appRecordDao().insertUser(User(firstName = "Tolhat", middleName = "Adelaja", surName = "Junaid"))
                    val user3=database.appRecordDao().insertUser(User(firstName = "Fawaz", middleName = "Tunji", surName = "Olaosebikan"))


                }
            }
        }
    }

}