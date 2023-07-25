pipeline {
    agent any

    // this section configures Jenkins options
    options {
        
        //Skip Default Checkout
        skipDefaultCheckout(true)
        
        // only keep 10 logs for no more than 10 days
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))

        // cause the build to time out if it runs for more than 12 hours
        timeout(time: 12, unit: 'HOURS')

        // add timestamps to the log
        timestamps()
    }

    // the pipeline section we all know and love: stages! :D
    stages {
        stage('Build') {

            steps {
                cleanWs()
                // Build Steps for Sample Java program
                git branch: 'dev-main', url: 'https://github.com/PratheeshJPK/jenkins-learning.git'
                sh('printenv')
                sh ('javac HelloWorld.java')
                sh('ls')
                sh('jar cvf HelloWorld.jar HelloWorld.class')
                sh ('ls')
                stash includes: '*.jar', name: 'app'
            }
        }
        stage('Test'){
            agent{
                label "agent1"
            }
            steps{
                cleanWs()
                // Build Steps for Sample Java program
                git branch: 'dev-main', url: 'https://github.com/PratheeshJPK/jenkins-learning.git'
                unstash 'app'
                sh ('ls')
                sh ('javac -cp HelloWorld.jar TestHelloWorld.java')
                sh ('ls')
                sh ('java -cp HelloWorld.jar:. TestHelloWorld')
                sh ('ls')
            }
        }
    }

    // the post section is a special collection of stages
    // that are run after all other stages have completed
    post {

        // the always stage will always be run
        always {
            archiveArtifacts allowEmptyArchive: true, artifacts: 'envvars.txt', followSymlinks: false
            // the always stage can contain build steps like other stages
            // a "steps{...}" section is not needed.
            echo "This step will run after all other steps have finished.  It will always run, even in the status of the build is not SUCCESS"
        }
    }
}
