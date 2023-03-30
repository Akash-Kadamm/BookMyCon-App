pipeline {
    agent {Dockerfile true}

    stages {
        stage('Build backend') {
            steps {
                // Checkout code from Git
                checkout scm
                
                // Build backend Docker image
                script {
                    docker.build("backend", "--file Dockerfile.backend .")
                }
            }
        }

        stage('Build frontend') {
            steps {
                // Build frontend Docker image
                script {
                    docker.build("frontend", "--file Dockerfile.frontend .")
                }
            }
        }

        stage('Deploy') {
            steps {
                // Start backend container
                script {
                    docker.run("backend", "-p 8080:8080 -d")
                }

                // Start frontend container
                script {
                    docker.run("frontend", "-p 3000:3000 -d")
                }
            }
        }
    }
}
