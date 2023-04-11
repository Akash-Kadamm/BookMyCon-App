pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.2'
        nodejs 'NodeJS 14.17.0'
    }
    
    stages {
        stage('Build') {
            steps {
                git url: 'https://github.com/Akash-Kadamm/BookMyCon-App.git'
                
                dir('backend') {
                    sh 'mvn clean package'
                }
                
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
    }
}
