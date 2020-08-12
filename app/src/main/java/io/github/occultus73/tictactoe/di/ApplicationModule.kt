package io.github.occultus73.tictactoe.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.occultus73.tictactoe.model.GameState
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideGameState(): GameState {
        return GameState()
    }

    @Singleton
    @Provides
    fun provideLiveGameState(gameState: GameState): LiveData<GameState> {
        return MutableLiveData(gameState)
    }
}















