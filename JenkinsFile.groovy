pipeline {
    agent any
    environment {
        MVN_CMD = "mvn clean test -Dsurefire.rerunFailingTestsCount=2" // Rerun all failed tests
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/A-Lampa/HomeProjectJobSearcher.git'
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    try {
                        sh "${MVN_CMD}"
                    } catch (Exception ex) {
                        echo "Tests failed, but retrying failed tests..."
                        sh "${MVN_CMD}"
                    }
                }
            }
        }
        stage('Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            script {
                archiveArtifacts artifacts: 'target/screenshots/*.png', allowEmptyArchive: true
            }
        }
        failure {
            echo "Tests failed! Check the logs and screenshots."
        }
    }
}