pipeline {
  agent any
  stages {
    stage('Build Java') {
      steps { sh 'mvn -B clean package -DskipTests' }
    }
    stage('Train ML') {
      steps {
        sh 'python -m pip install -r ml-service/requirements.txt'
        sh 'python ml-service/train_model.py'
      }
    }
    stage('Frontend Build') {
      steps {
        dir('frontend') {
          sh 'npm install'
          sh 'npm run build'
        }
      }
    }
  }
}
