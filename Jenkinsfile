pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
                sh 'cd client && npm install && npm run build'
            }
        }
    }
}
