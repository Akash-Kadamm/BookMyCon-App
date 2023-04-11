pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.2'
        nodejs 'NodeJS 14.17.0'
    }
    
    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/Akash-Kadamm/BookMyCon-App.git'
                
                dir('BookMyConAPI') {
//                     sh 'mvn -f /path/to/pom.xml clean install'
                    sh 'mvn clean package'
//                     sh 'mvn clean install'
                }
                
                dir('bookmycon-ui') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
    }
}
