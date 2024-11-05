pipeline {
    agent any
    tools {
        maven 'MAVEN_3_9_9'
        jdk 'JDK_17'
    }

    stages {
        stage ('Compile Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_9_9') {
                    if (isUnix()) {
                        sh 'mvn clean compile'
                    } else {
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        stage ('Testing Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_9_9') {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
        }
        stage ('package Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_9_9') {
                    if (isUnix()) {
                        sh 'mvn package'
                    } else {
                        bat 'mvn package'
                    }
                }
            }
        }
    }
}