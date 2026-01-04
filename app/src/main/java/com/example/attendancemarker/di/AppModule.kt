package com.example.attendancemarker.di

import android.content.Context
import androidx.room.Room
import com.example.attendancemarker.data.AndroidBluetoothController
import com.example.attendancemarker.data.database.AppDatabase
import com.example.attendancemarker.ui.theme.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)

    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "attendance_db"
        ).build()
    }

    @Provides
    fun provideStudentDao(db: AppDatabase) = db.StudentDao()

    @Provides
    fun provideAttendanceDao(db: AppDatabase) = db.attendanceDap()
}