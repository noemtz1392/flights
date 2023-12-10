object Dependencies {

    val app = listOf(
        Dependency.Startup,
        Dependency.Timber,
        Dependency.Hilt,
        Dependency.Ksp.Hilt,
    )

    val data = listOf(
        Dependency.Coroutines,
        Dependency.DateTime,
        Dependency.Hilt,
        Dependency.Ksp.Hilt,
        Dependency.OkHttp,
        Dependency.OkHttpLogging,
        Dependency.Retrofit,
        Dependency.RetrofitMoshiConverter,
        Dependency.Moshi,
        Dependency.MoshiAdapter,
        Dependency.Ksp.MoshiCodegen,
        Dependency.Timber,
    )

    val domain = listOf(
        Dependency.Coroutines,
        Dependency.Dagger,
        Dependency.Ksp.Dagger,
    )

    val presentation = listOf(
        Dependency.Hilt,
        Dependency.Ksp.Hilt,
        Dependency.Timber
    )
}