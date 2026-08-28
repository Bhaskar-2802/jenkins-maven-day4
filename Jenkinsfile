pipeline {

    agent any

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'qa', 'prod'],
            description: 'Choose the deployment environment'
        )
    }

    environment {
        APP_NAME = 'jenkins-maven-day4'
        ENVIRONMENT = 'dev'
    }

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

        stage('Parallel Checks') {
            parallel {

                stage('Unit Test Check') {
                    steps {
                        echo 'Running Unit Test Check...'
                        bat 'mvn test'
                    }
                }

                stage('Code Quality Check') {
                    steps {
                        echo 'Running Code Quality Check...'
                        bat 'mvn -q test'
                    }
                }

                stage('Security Check') {
                    steps {
                        echo 'Running Security Check...'
                        echo 'Security scan simulation completed.'
                    }
                }
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

        stage('Use Secret') {
            steps {
                withCredentials([
                    string(
                        credentialsId: 'demo-secret',
                        variable: 'MY_SECRET'
                    )
                ]) {
                    echo 'Secret was successfully loaded into the pipeline.'
                    echo "Secret length: ${MY_SECRET.length()}"
                }
            }
        }

        stage('Deploy to DEV') {
            when {
                expression {
                    params.DEPLOY_ENV == 'dev'
                }
            }

            steps {
                echo 'Deploying application to DEV environment...'
            }
        }

        stage('Deploy to QA') {
            when {
                expression {
                    params.DEPLOY_ENV == 'qa'
                }
            }

            steps {
                echo 'Deploying application to QA environment...'
            }
        }

        stage('Production Approval') {
            when {
                expression {
                    params.DEPLOY_ENV == 'prod'
                }
            }

            steps {
                input(
                    message: 'Are you sure you want to deploy to PRODUCTION?',
                    ok: 'Approve Production Deployment'
                )
            }
        }

        stage('Deploy to PROD') {
            when {
                expression {
                    params.DEPLOY_ENV == 'prod'
                }
            }

            steps {
                echo 'Deploying application to PROD environment...'
            }
        }
    }

    post {

        success {
            echo '======================================'
            echo 'PIPELINE SUCCESSFUL!'
            echo "Application: ${env.APP_NAME}"
            echo "Environment: ${params.DEPLOY_ENV}"
            echo 'Artifact archived successfully.'
            echo '======================================'
        }

        failure {
            echo '======================================'
            echo 'PIPELINE FAILED!'
            echo 'Please check the Console Output.'
            echo '======================================'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}