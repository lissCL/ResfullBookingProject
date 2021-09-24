pipeline{
    agent any
    stages
            {
                stage('Download code')
                        {
                            steps{
                                git 'https://github.com/lissCL/ResfullBookingProject.git'
                            }
                        }
                stage('Run tests')
                        {
                            steps{
                                withGradle{
                                    sh '/var/lib/jenkins/tools/hudson.plugins.gradle.GradleInstallation/Gradle6.8/bin/gradle clean test'
                                }
                            }
                        }
            }
    post{
        always{
            script{
                allure([
                        includeProperties: false,
                        jdk				 : '',
                        properties 		 : [],
                        reportBuildPolicy: 'ALWAYS',
                        results 		 : [[path:'build/allure-results']]
                ])
            }
        }
    }
}