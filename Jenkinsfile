pipeline{
    agent none
   
    stages{
      stage("build-back-end"){
          agent {
              docker { image 'Maven:3.9.1' }  
          }
          steps{
          sh"mvn --version"
            echo'building automatically test'
        }
      }
        stage("build-front-end")
        {
            agent
            {
                docker { image 'node:16.13.1-alpine' }
            }
            steps
            {
                sh'node --version'
            }
        }
            
      stage("test"){
        steps{
          echo 'building aplication'
        }
        }
    
      stage("deploy"){
        steps{
          echo 'building aplication'
        }
        }
      
    }
}
