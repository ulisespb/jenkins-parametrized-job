job('job-dsl-example-2') {
  description('job-dsl-example-2-description')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName ('ulisespb')
      node / gitConfigEmail ('ulisesperezb08.dev@gmail.com')
    }
  }
  parameters {
    stringParam('name', defaultValue = 'Ulises', description = 'String param for name')
    choiceParam('course', ['Course1', 'Course2', 'Course3', 'Course4', 'Course5'], description = 'Choice param for course')
    booleanParam('enable', false)
  }
  triggers {
    cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('ulisesperezb08.dev@gmail.com', true, true)
	slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
