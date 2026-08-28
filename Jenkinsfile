pipeline {

    agent any

    // Day 6: Build Parameters
    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'qa', 'prod'],
            description: 'Choose the environment'
        )
    }

    // Day 6: Environment Variables
    environment {
        APP_NAME = 'jenkins-maven-day4'
        ENVIRONMENT = 'dev'
    }

    // Maven configuration
    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Source code checked out by Jenkins'
            }
        }

        stage('Environment Info') {
            steps {
                echo "Application: ${env.APP_NAME}"
                echo "Default Environment: ${env.ENVIRONMENT}"
            }
        }

        stage('Selected Environment') {
            steps {
                echo "Selected Environment: ${params.DEPLOY_ENV}"
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java application...'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Creating JAR artifact...'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo 'Archiving JAR artifact...'

                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    fingerprint: true
                )
            }
        }
    }

    post {

        success {
            echo '======================================'
            echo 'Maven pipeline completed successfully!'
            echo "Application: ${env.APP_NAME}"
            echo "Environment: ${params.DEPLOY_ENV}"
            echo 'JAR artifact has been archived.'
            echo '======================================'
        }

        failure {
            echo '======================================'
            echo 'Maven pipeline FAILED!'
            echo '======================================'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}