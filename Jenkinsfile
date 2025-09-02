pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.9'   // Jenkins मधे configure केलेले Maven tool name
        jdk 'JDK-11'          // Jenkins मधे configure केलेले JDK tool name
    }

    environment {
        TOMCAT_USER = 'admin'
        TOMCAT_PASS = 'admin123'
        TOMCAT_URL  = 'http://localhost:8080/manager/text'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mayuridndhre/jenkins-maven-demo.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deploy to Tomcat') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                sh """
                curl -u ${TOMCAT_USER}:${TOMCAT_PASS} \\
                     --upload-file target/*.war \\
                     "${TOMCAT_URL}/deploy?path=/pipelineapp&update=true"
                """
            }
        }
    }
}
