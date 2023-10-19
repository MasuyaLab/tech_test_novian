object Dependency {

    object Core {
        const val coreKtx = "androidx.core:core-ktx:${Versions.Core.coreKtxVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Core.appCompatVersion}"

        const val coreNavComp = "androidx.navigation:navigation-fragment-ktx:${Versions.Core.navigationComponentVersion}"
        const val navCompKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Core.navigationComponentVersion}"
        const val navCompPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Core.navigationComponentVersion}"

        const val androidMaterial = "com.google.android.material:material:${Versions.Core.materialVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Core.constraintLayoutVersion}"
        const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.Core.coordinatorLayout}"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.Core.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.Core.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.Core.roomVersion}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Core.retrofitVersion}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.Core.retrofitVersion}"
        const val gson = "com.google.code.gson:gson:${Versions.Core.retrofitVersion}"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Core.okhttpVersion}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Core.okhttpVersion}"
    }

    object ViewModel {
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Core.viewModelVersion}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Core.viewModelVersion}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.core}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Core.hiltVersion}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Core.hiltVersion}"

        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Core.hiltVersion}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Core.glideVersion}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.Core.glideVersion}"
    }

    object UnitTest {
        const val junit = "junit:junit:${Versions.UnitTest.jUnitVersion}"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:${Versions.AndroidTest.jUnitVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoVersion}"
    }

    object SkeletonLayout {
        const val core = "com.ericktijerou.koleton:koleton:${Versions.SkeletonLayout.core}"
    }

    object Shimmer {
        const val core = "com.facebook.shimmer:shimmer:${Versions.Shimmer.core}"
    }

    val appLibraries = arrayListOf<String>().apply {
        // Core Region
        add(Core.coreKtx)
        add(Core.appCompat)
        add(Core.coreNavComp)
        add(Core.navCompKtx)
        add(Core.androidMaterial)
        add(Core.constraintLayout)
        add(Core.coordinatorLayout)

        // Room Region
        add(Room.roomKtx)
        add(Room.roomRuntime)

        // Network Region
        add(Retrofit.retrofit)
        add(Retrofit.gson)
        add(Retrofit.gsonConverter)
        add(OkHttp.okhttp)
        add(OkHttp.interceptor)

        // ViewModel Region
        add(ViewModel.viewModelKtx)
        add(ViewModel.liveData)

        // Coroutines Region
        add(Coroutines.core)

        // Hilt Region
        add(Hilt.hilt)

        // Glide Region
        add(Glide.glide)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(UnitTest.junit)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(AndroidTest.espresso)
        add(AndroidTest.junit)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(Hilt.compiler)
        add(Room.roomCompiler)
        add(Glide.compiler)
    }

}