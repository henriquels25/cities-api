pipeline {
    agent { docker { image 'openjdk:11-jdk-slim' } }
    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE = 'sqlite'
    }
    stages {
        stage('build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Deploy - Staging') {
            steps {
                echo 'Deploying to staging'
            }
        }

        stage('Production deploy confirmation') {
            steps {
                input 'Can the software be deployed in production?'
            }
        }

        stage('Deploy - Production') {
            steps {
                echo 'Deploying to production'
            }
        }

    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/test-results/**/*.xml'
        }
    }
}