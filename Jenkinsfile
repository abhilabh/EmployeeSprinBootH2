pipeline {
    agent any
    tools { 
        maven 'Maven 3.9.4' 
        
    }
    environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
    
    stages {
        stage('Build JAR with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("abhilabh/employee:latest")
                }
            }
        }
        
        stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
        
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        docker.image("abhilabh/employee:latest").push()
                    }
                }
            }
        }
        
        stage('Deploy Container') {
    steps {
        script {
            // Remove any existing container with the same name
            try {
                docker.image("abhilabh/employee:latest").remove()
            } catch (Exception e) {
                // Ignore any errors if the container doesn't exist
            }

            // Run the new container
            docker.image("abhilabh/employee:latest").run("-p 8081:9997")
        }
    }
}
    }
}
