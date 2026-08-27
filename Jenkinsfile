pipeline {
    agent any

    tools {
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'GitHub automatically triggered Jenkins!'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Maven pipeline completed successfully!'
        }

        failure {
            echo 'Maven pipeline failed.'
        }
    }
}