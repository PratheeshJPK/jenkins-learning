pipeline {
    agent any
    parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
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
        stage('EnvironmentInfo') {

            agent {
                label "agent1"
            }
            
            environment {
               Myname = "Lokesh"
            }

            steps {
                 echo "${params.Greeting} World!"
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
