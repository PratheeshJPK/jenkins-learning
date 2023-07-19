pipeline {
    agent any

    // this section configures Jenkins options
    options {

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

            agent any

            steps {
                // Build Steps for Sample Java program
                sh ('javac HelloWord.java')
                sh('ls')
                sh('jar cvf HelloWorld.jar HelloWorld.class')
            }
        }
        stage('Test'){
            agent{
                label "agent1"
            }
            steps{
                sh ('javac TestHelloWorld.java')
                sh ('ls')
                sh ('java -cp HelloWorld.jar;. TestHelloWorld')
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
