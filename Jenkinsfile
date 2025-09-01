pipeline {
    agent any
    
    tools {
    maven 'Maven-3.9.9'   // Jenkins मधे दिसणारं Maven tool name
    jdk 'JDK-11'          // Jenkins मधे दिसणारं JDK tool name
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
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Tomcat') {
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
