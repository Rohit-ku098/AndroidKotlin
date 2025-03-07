package com.example.hilt_di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@InstallIn(ActivityComponent::class)
@Module
class UserModule {
    @Provides
    @Named("room")
    fun provideRoomUserRepository(roomUserRepository: RoomUserRepository): UserRepository {
        return roomUserRepository
    }

    @Provides
    @Named("firebase")
    fun provideFirebaseUserRepository(firebaseUserRepository: FirebaseUserRepository): UserRepository {
        return firebaseUserRepository
    }
}