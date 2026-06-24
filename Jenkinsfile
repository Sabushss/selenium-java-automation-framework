// Jenkinsfile - CI/CD Pipeline for Selenium Automation Framework
// Author: Subash A | QA Automation Engineer

pipeline {
    agent any

    tools {
        maven 'Maven_3.9'
        jdk 'JDK_11'
    }

    environment {
        BROWSER        = 'chrome'
        HEADLESS       = 'true'
        REPORT_DIR     = 'test-output'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building project with Maven...'
                sh 'mvn clean compile -q'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                echo 'Running Smoke Tests...'
                sh 'mvn test -Dbrowser=${BROWSER} -Dheadless=${HEADLESS} -Dgroups=smoke'
            }
            post {
                always {
                    publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Run Regression Suite') {
            steps {
                echo 'Running Full Regression Suite...'
                sh 'mvn test -Dbrowser=${BROWSER} -Dheadless=${HEADLESS}'
            }
            post {
                always {
                    publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                }
            }
        }

        stage('Publish Report') {
            steps {
                echo 'Publishing Extent Reports...'
                publishHTML(target: [
                    allowMissing         : false,
                    alwaysLinkToLastBuild: true,
                    keepAll              : true,
                    reportDir            : 'test-output',
                    reportFiles          : 'ExtentReport.html',
                    reportName           : 'Selenium Test Report'
                ])
            }
        }
    }

    post {
        success {
            echo 'All tests passed successfully!'
        }
        failure {
            echo 'Some tests failed. Check the report for details.'
            archiveArtifacts artifacts: 'test-output/screenshots/**/*.png', allowEmptyArchive: true
        }
        always {
            cleanWs()
        }
    }
}
