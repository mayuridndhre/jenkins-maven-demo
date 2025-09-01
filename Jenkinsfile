pipeline {
    agent any
    
    tools {
        maven 'Maven3'   // Jenkins मध्ये configure केलेलं Maven नाव
        jdk 'Java11'     // Jenkins मध्ये configure केलेलं JDK नाव
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
