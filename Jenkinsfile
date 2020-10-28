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
                script {
                    log.info 'build'
                }
                //sh 'gradle clean build -x test'
            }
        }

        stage('unit test') {
            steps {
                script {
                    log.warning 'unit test'
                }
                //sh 'gradle unitTest'
            }
        }

        stage('integration test') {
            steps {
                echo 'integration test'
                //sh 'gradle integrationTest'
            }
        }

        stage('sonar') {
            steps {
                echo 'sonar'
                //sh 'gradle jacocoTestReport sonarqube -x test -Dsonar.branch.name=${BRANCH_NAME}'
            }
        }

        stage('e2e test') {
            steps {
                echo 'e2eTest'
                //sh 'gradle e2eTest'
            }
        }

        stage('Deploy - Staging') {
            when {
                branch 'main'
            }
            steps {
                script {
                    log.info 'Deploying to staging latest version'
                }
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
                script {
                    log.warning 'Deploying to production version ${TAG_NAME}'
                }
            }
        }

    }

//    post {
//        always {
//            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
//            junit 'build/test-results/**/*.xml'
//        }
//    }
}