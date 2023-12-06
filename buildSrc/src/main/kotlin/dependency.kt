sealed class Dependency(
    private val versionNumber: String,
    private val packageName: String
) {
    object Coroutines : Dependency(
        versionNumber = Versions.COROUTINES,
        packageName = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
    )

    object Dagger : Dependency(
        versionNumber = Versions.DAGGER,
        packageName = "com.google.dagger:dagger"
    )

    object Hilt : Dependency(
        versionNumber = Versions.HILT,
        packageName = "com.google.dagger:hilt-android"
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
    }

    fun full() = "$packageName:$versionNumber"
}