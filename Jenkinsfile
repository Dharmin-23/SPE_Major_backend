pipeline {
     environment{
        dockerimage=""
    }
    agent any
    stages {
        stage('Git clone') {
            steps {
            git branch: 'main',credentialsId:'Github-credentials',url: 'https://github.com/Shubhamp194/Swapsie-backend.git'
            }
        }

        stage("Running Test cases"){
            steps{
                sh "mvn clean test"
            }
        }
         
        stage("Maven Build"){
            steps{
                sh "mvn clean install"
            }
        }

        stage('Docker Build Image') {
            steps {
                script{
                    dockerimage=docker.build "shubhamp194/swapsie-backend-image"
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script{
                    docker.withRegistry('','docker-jenkins'){
                    dockerimage.push()
                    }
                }
            }
        }

    }
}
