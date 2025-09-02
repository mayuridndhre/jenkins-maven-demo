pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.9'   // Jenkins मध्ये configure केलेले Maven tool name
        jdk 'JDK-11'          // Jenkins मध्ये configure केलेले JDK tool name
    }

    environment {
        TOMCAT_USER = 'admin'
        TOMCAT_PASS = 'admin123'
        TOMCAT_URL  = 'http://localhost:8080/manager/text'
    }

    stages {
        // 1️⃣ Checkout Stage
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mayuridndhre/jenkins-maven-demo.git'
            }
        }

        // 2️⃣ Build Stage
        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        // 3️⃣ Test Stage
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

        // 4️⃣ SonarQube Analysis Stage
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {  // Jenkins मध्ये configure केलेले SonarQube server name
                    sh 'mvn sonar:sonar -Dsonar.projectKey=pipeline-demo'
                }
            }
        }

        // 5️⃣ Quality Gate Check
        stage('Quality Gate') {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }

        // 6️⃣ Deploy to Tomcat Stage
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

    post {
        success {
            echo "✅ Build + Test + SonarQube Analysis + Deploy Passed!"
        }
        failure {
            echo "❌ Pipeline Failed!"
        }
    }
}
