pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK11', type: 'jdk'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Matheus-Malara/prova2-MA.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '*/target/surefire-reports/.xml'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline sucesso.'
        }
        failure {
            echo 'Pipeline falha.'
        }
    }
}