def deployScripts;

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
                echo 'build'
                //sh 'gradle clean build -x test'
            }
        }

        stage('unit test') {
            steps {
                echo 'unit test'
                //sh 'gradle unitTest'
            }
        }

        stage('integration test') {
            steps {
                sh 'gradle integrationTest'
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
                    deployScripts = load("deploy_scripts.groovy")
                    deployScripts.deployToStaging("cities-api", "0.0.1")
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
                    deployScripts.deployToProduction("cities-api", "0.0.1")
                }
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