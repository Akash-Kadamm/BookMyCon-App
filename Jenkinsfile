pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.2'
        nodejs 'NodeJS 18.14.0'
    }
    
    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/Akash-Kadamm/BookMyCon-App.git'
                
//                 dir('BookMyConAPI') {
// //                     sh 'mvn -f /path/to/pom.xml clean install'
//                     sh 'mvn clean package'
// //                     sh 'mvn clean install'
//                 }
                
//                 dir('bookmycon-ui') {
//                     sh 'npm install'
//                     sh 'npm run build'
//                 }
                 sh 'docker build -t bookmycon .'
            }
        }
        stage('Deploy') {
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/') {
            docker.image('bookmycon:latest').push()
          }
        }
      }
    }
  }   
}
