import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kaptImplement(list: List<String>) {
    list.forEach { dep ->
        add("kapt", dep)
    }
}

fun DependencyHandler.implement(list: List<String>) {
    list.forEach { dep ->
        add("implementation", dep)
    }
}

fun DependencyHandler.androidTestImplement(list: List<String>) {
    list.forEach { dep ->
        add("androidTestImplementation", dep)
    }
}

fun DependencyHandler.testImplement(list: List<String>) {
    list.forEach { dep ->
        add("testImplementation", dep)
    }
}