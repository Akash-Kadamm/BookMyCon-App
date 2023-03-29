pipeline {
    agent any
    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=./.m2/repository"
        NODE_HOME = tool 'NodeJS'
        PATH = "${env.NODE_HOME}/bin:${env.PATH}"
        REACT_APP_BACKEND_URL = "http://localhost:8080"
    }
    stages {
        stage('Install Maven') {
  steps {
    sh '''
    curl -o apache-maven-3.8.1-bin.tar.gz https://apache.osuosl.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz
    tar -xzf apache-maven-3.8.1-bin.tar.gz
    export PATH=$PATH:$(pwd)/apache-maven-3.8.1/bin
    '''
  }
}


        stage('Build Backend') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'cp backend/target/my-app.jar .'
                sh 'cp -r frontend/build/* static/'
                archiveArtifacts artifacts: '**/*', fingerprint: true
            }
        }
    }
}

