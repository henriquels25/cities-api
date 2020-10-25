pipeline {
    agent { docker { image 'openjdk:11-jdk-slim' } }
    environment {
        SONAR_TOKEN = credentials('sonarcloud-token')
    }
    stages {
        stage('Build + Code Quality') {
            parallel {
                stage('build') {
                    steps {
                        echo 'building'
                    }
                }

                stage('sonar') {
                    steps {
                        sh './gradlew sonarqube'
                    }
                }
            }
        }

        stage('Deploy - Staging') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying to staging'
            }
        }

        stage('Production deploy confirmation') {
            when {
                buildingTag()
            }
            steps {
                input 'Can the software be deployed in production?'
            }
        }

        stage('Deploy - Production') {
            when {
                buildingTag()
            }
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