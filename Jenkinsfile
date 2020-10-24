pipeline {
    agent { docker { image 'openjdk:11-jdk-slim' } }
    stages {
        stage('test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}