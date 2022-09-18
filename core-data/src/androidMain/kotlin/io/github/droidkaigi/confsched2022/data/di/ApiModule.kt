package io.github.droidkaigi.confsched2022.data.di

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.droidkaigi.confsched2022.core.data.BuildConfig
import io.github.droidkaigi.confsched2022.data.NetworkService
import io.github.droidkaigi.confsched2022.data.SettingsDatastore
import io.github.droidkaigi.confsched2022.data.auth.AuthApi
import io.github.droidkaigi.confsched2022.data.auth.Authenticator
import io.github.droidkaigi.confsched2022.data.auth.AuthenticatorImpl
import io.github.droidkaigi.confsched2022.data.sessions.defaultKtorConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
public class ApiModule {
    @Provides
    @Singleton
    public fun provideNetworkService(
        httpClient: HttpClient,
        authApi: AuthApi
    ): NetworkService {
        return NetworkService(httpClient, authApi)
    }

    @Provides
    @Singleton
    public fun provideAuthApi(
        httpClient: HttpClient,
        settingsDatastore: SettingsDatastore,
        authenticator: Authenticator
    ): AuthApi {
        return AuthApi(httpClient, settingsDatastore, authenticator)
    }

    @Provides
    @Singleton
    public fun provideHttpClient(
        okHttpClient: OkHttpClient,
        settingsDatastore: SettingsDatastore
    ): HttpClient {
        val httpClient = HttpClient(OkHttp) {
            engine {
                config {
                    preconfigured = okHttpClient
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        }
                    )
                }
            }
            defaultKtorConfig(settingsDatastore)
        }
        return httpClient
    }

    @Provides
    @Singleton
    public fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = BASIC
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    public fun provideAuthenticator(): Authenticator {
        return AuthenticatorImpl()
    }

    private val Context.dataStore by preferencesDataStore(
        name = SettingsDatastore.NAME,
    )

    @Provides
    @Singleton
    public fun provideFlowSettings(application: Application): FlowSettings {
        return DataStoreSettings(datastore = application.dataStore)
    }

    @Provides
    @Singleton
    public fun providePreferenceDatastore(flowSettings: FlowSettings): SettingsDatastore {
        return SettingsDatastore(flowSettings)
    }
}
