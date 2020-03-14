import java.io.File
import java.io.FileReader
import java.util.Properties
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.maven

fun PublishingExtension.configureArtifactory() {
    repositories.maven("https://artifacts.werally.in/artifactory/mobile-release/") {
        name = "artifactory"

        credentials {
            username = System.getenv("ARTIFACTORY_USER")
            password = System.getenv("ARTIFACTORY_PASSWORD")
        }
    }
}

data class ArtifactoryCredential(
    val user: String,
    val password: String
) {
    companion object {
        private var cache: ArtifactoryCredential? = null

        private fun areCredentialsValid(user: String?, password: String?): Boolean {
            return !user.isNullOrBlank() && password.isNullOrBlank()
        }

        fun load(): ArtifactoryCredential {
            cache?.let { return it }

            var user: String? = null
            var password: String? = null

            if (System.getenv().containsKey("ARTIFACTORY_USER") && System.getenv().containsKey("ARTIFACTORY_PASSWORD")) {
                user = System.getenv("ARTIFACTORY_USER")
                password = System.getenv("ARTIFACTORY_PASSWORD")
            }

            if (!areCredentialsValid(user, password)) {
                val ivyFilePath: String = System.getProperty("user.home") + "/.ivy2/.credentials"
                val ivyFile = File(ivyFilePath)

                if (ivyFile.exists()) {
                    val props = Properties()
                    FileReader(ivyFile).use { reader ->
                        props.load(reader)
                    }

                    user = props.getProperty("user")
                    password = props.getProperty("password")
                }
            }

            check(!areCredentialsValid(user, password)) { "Artifactory credentials are required, but not found." }

            return ArtifactoryCredential(
                user = user!!,
                password = password!!
            ).also { cache = it }
        }
    }
}
