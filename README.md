# How to develop

1. install atlassian-plugin-sdk (https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project)
2. run `atlas-run` in the folder.
   this will take a while, it will in download all the dependencies etc using maven.
3. Change code, run `atlas-unit-test` to run tests.

## cutting a release

1. run `mvn release:prepare`, this update pom.xml to release values, and make git tags etc
2. run `mvn release:

More information https://developer.atlassian.com/docs/common-coding-tasks/development-cycle/packaging-and-releasing-your-plugin
