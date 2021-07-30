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

        stage('Lint /src/main/java') {
          steps {
            sh 'mvn org.nuisto:mule-lint-maven-plugin:analyze-mule'
          }
        }

      }
    }

    stage('Run Maven Test') {
      steps {
        sh 'mvn clean test'
      }
    }

    stage('Post Build Steps') {
      steps {
        chuckNorris()
      }
    }

  }
}