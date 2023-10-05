pipeline {
    agent any
    
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
        
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image("abhilabh/employee:latest").push()
                    }
                }
            }
        }
        
        stage('Deploy Container') {
            steps {
                script {
                    // Stop and remove any existing container
                    docker.image("abhilabh/employee:latest").stop()
                    docker.image("yabhilabh/employee:latest").remove()

                    // Run the new container
                    docker.image("your-dockerhub-username/your-spring-boot-app:latest").run("-p 8081:9997", "--name employee")
                }
            }
        }
    }
}
