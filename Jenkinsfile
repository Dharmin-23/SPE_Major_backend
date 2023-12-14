pipeline {
     environment{
        dockerimage=""
    }
    agent any
    stages {
        stage('1: Git clone') {
            steps {
            git branch: 'main',credentialsId:'Github-credentials',
            url: 'https://github.com/Dharmin-23/SPE_Major_backend'
            }
        }

        stage("2: Running Test cases"){
            steps{
                sh "mvn clean test"
            }
        }
         
        stage("3: Maven Build"){
            steps{
                sh "mvn clean install"
            }
        }

        stage('4: Docker Build Image') {
            steps {
                script{
                    dockerimage=docker.build "dharmin23/swapsie-backend"
                }
            }
        }
        stage('5: Push Docker Image') {
            steps {
                script{
                    docker.withRegistry('','DockerHubDharminCreds'){
                    dockerimage.push()
                    }
                }
            }
        }
        
        stage('6 : Clean docker images'){
            steps{
                script{
                    sh 'docker container prune -f'
                    sh 'docker image prune -f'
                }
            }
        }
    }
}
