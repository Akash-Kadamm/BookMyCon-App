pipeline {
    agent {
        dockerfile {
            filename 'Docker'
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
