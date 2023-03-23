pipeline{
    agent any
    tools{
        maven "Maven 3.9.1"
    
    }
    stages{
      stage("build"){
        steps{
          sh"mvn -version"
            echo'building automatically test'
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
