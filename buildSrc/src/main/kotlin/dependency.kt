sealed class Dependency(
    private val versionNumber: String,
    private val packageName: String
) {
    fun full() = "$packageName:$versionNumber"

    object Coroutines : Dependency(
        versionNumber = Versions.COROUTINES,
        packageName = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
    )

    object Startup : Dependency(
        versionNumber = Versions.STARTUP,
        packageName = "androidx.startup:startup-runtime"
    )

    object DateTime : Dependency(
        versionNumber = Versions.DATE_TIME,
        packageName = "org.jetbrains.kotlinx:kotlinx-datetime"
    )

    object Timber : Dependency(
        versionNumber = Versions.TIMBER,
        packageName = "com.jakewharton.timber:timber"
    )

    object Dagger : Dependency(
        versionNumber = Versions.DAGGER,
        packageName = "com.google.dagger:dagger"
    )

    object Hilt : Dependency(
        versionNumber = Versions.HILT,
        packageName = "com.google.dagger:hilt-android"
    )

    object OkHttp : Dependency(
        versionNumber = Versions.OK_HTTP,
        packageName = "com.squareup.okhttp3:okhttp"

    )

    object OkHttpLogging : Dependency(
        versionNumber = Versions.OK_HTTP,
        packageName = "com.squareup.okhttp3:logging-interceptor"

    )

    object Retrofit : Dependency(
        versionNumber = Versions.RETROFIT,
        packageName = "com.squareup.retrofit2:retrofit"

    )

    object RetrofitMoshiConverter : Dependency(
        versionNumber = Versions.RETROFIT,
        packageName = "com.squareup.retrofit2:converter-moshi"
    )

    object Moshi : Dependency(
        versionNumber = Versions.MOSHI,
        packageName = "com.squareup.moshi:moshi"
    )

    object MoshiAdapter : Dependency(
        versionNumber = Versions.MOSHI,
        packageName = "com.squareup.moshi:moshi-adapters"
    )

    object MoshiKotlin : Dependency(
        versionNumber = Versions.MOSHI,
        packageName = "com.squareup.moshi:moshi-kotlin"
    )

    sealed class Ksp(
        private val versionNumber: String,
        private val packageName: String
    ) : Dependency(versionNumber, packageName) {

        object Dagger : Ksp(
            versionNumber = Versions.DAGGER,
            packageName = "com.google.dagger:dagger-compiler"
        )

        object Hilt : Ksp(
            versionNumber = Versions.HILT,
            packageName = "com.google.dagger:hilt-android-compiler"
        )

        object MoshiCodegen : Ksp(
            versionNumber = Versions.MOSHI,
            packageName = "com.squareup.moshi:moshi-kotlin-codegen"
        )
    }
}