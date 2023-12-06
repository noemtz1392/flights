object Dependencies {

    val app = listOf(
        Dependency.Hilt,
        Dependency.Ksp.Hilt,
    )

    val data = listOf(
        Dependency.Hilt,
        Dependency.Ksp.Hilt,
    )

    val domain = listOf(
        Dependency.Coroutines,
        Dependency.Dagger,
        Dependency.Ksp.Dagger,
    )

    val presentation = listOf(
        Dependency.Hilt,
        Dependency.Ksp.Hilt
    )
}