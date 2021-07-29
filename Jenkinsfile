pipeline {
  agent any
  stages {
    stage('Tool Version Check') {
      parallel {
        stage('Tool Version Check') {
          steps {
            sh '''mvn --version
git --version
java -version
javac -version'''
          }
        }

        stage('Verify POM file') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Run Maven Test') {
      steps {
        sh 'mvn clean'
      }
    }

  }
}