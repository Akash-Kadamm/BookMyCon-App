pipeline{
    agent any 
//     {
//         docker
//         {
//             image 'node:16.13.1-alpine'
//         }
//     }
   
    stages{
//       stage("build-back-end"){
//           agent {
//               docker { image 'maven:3.9.0-eclipse-temurin-11' }  
//           }
//           steps{
//            sh"mvn --version"
//             echo'building automatically test'
//         }
//       }
        stage("build-front-end")
        {
            agent
            {
                docker { image 'node:16-alpine' }
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
