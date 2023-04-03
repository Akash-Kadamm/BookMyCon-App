pipeline {
    agent {
        dockerfile {
            filename 'DockerFile'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
                sh 'cd client && npm install && npm run build'
            }
        }
    }
}
