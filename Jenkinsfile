@Library('jenkins-shared-library') _

pipeline {
    agent {
        docker {
            image 'gradle:6.6.1-jdk11-hotspot'
            args '-v $HOME/.gradle:/root/.gradle'
        }
    }
    environment {
        SONAR_TOKEN = credentials('sonarcloud-token')
    }
    stages {
        stage('build') {
            steps {
                sh 'gradle clean build -x test'
            }
        }

        stage('unit test') {
            steps {
                sh 'gradle unitTest'
            }
        }

        stage('integration test') {
            steps {
                sh 'gradle integrationTest'
            }
        }

        stage('sonar') {
            when {
                not {
                    buildingTag()
                }
            }
            steps {
                sh 'gradle jacocoTestReport sonarqube -x test -Dsonar.branch.name=${BRANCH_NAME}'
            }
        }

        stage('e2e test') {
            steps {
                sh 'gradle e2eTest'
            }
        }

        stage('Deploy - Staging') {
            when {
                branch 'main'
            }
            steps {
                deployToStaging 'cities-api', 'latest'
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
                deployToProduction 'cities-api', env.TAG_NAME
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