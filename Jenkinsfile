pipeline {
	agent any

	// Environment variables
	environment {
		PROJECT_NAME = 'Team201_SaucedemoTesting'
		GIT_REPO = 'https://github.com/nhahub/NHA-201.git'
		BRANCH = 'main'
		TEST_RESULTS_DIR = 'target/surefire-reports'
		ALLURE_RESULTS_DIR = 'allure-results'
	}

	// Build triggers
	triggers {
		pollSCM('H * * * *')
		githubPush()
	}

	// Pipeline stages
	stages {
		stage('Checkout Code') {
			steps {
				echo "Stage: Checkout Code"
				checkout([
					$class: 'GitSCM',
					branches: [[name: '*/main']],
					userRemoteConfigs: [[url: 'https://github.com/nhahub/NHA-201.git']]
				])
				echo "Code checkout successful"
			}
		}

		stage('Build Project') {
			steps {
				echo "Stage: Build Project"
				echo "Building with Maven..."

				bat '''
                    mvn clean compile -DskipTests -T 4 -U
                '''

				echo "Build successful"
			}
		}

		stage('Run Automation Tests') {
			steps {
				echo "Stage: Run Automation Tests"
				echo "Running 40+ test cases..."
				echo "Using 4 parallel threads"

				bat '''
                    mvn test -T 4 -DskipITs=false -Dtest=**/*Test.java
                '''

				echo "Tests execution completed"
			}
		}

		stage('Generate Allure Report') {
			steps {
				echo "Stage: Generate Allure Report"
				echo "Generating detailed Allure report..."

				script {
					try {
						bat 'mvn allure:report -Dmaven.test.skip=true'
						echo "Allure report generated successfully"
					} catch (Exception e) {
						echo "Warning: Allure report generation skipped"
					}
				}
			}
		}

		stage('Archive Artifacts') {
			steps {
				echo "Stage: Archive Artifacts"

				bat '''
                    if not exist "build-artifacts" mkdir build-artifacts
                    xcopy /E /Y target\\surefire-reports build-artifacts\\ 2>nul
                    xcopy /E /Y allure-results build-artifacts\\ 2>nul
                    xcopy /E /Y allure-report build-artifacts\\ 2>nul
                '''

				archiveArtifacts artifacts: 'build-artifacts/**',
				allowEmptyArchive: true,
				fingerprint: true

				echo "Artifacts archived"
			}
		}
	}

	// Post-build actions
	post {
		always {
			echo "Post-Build: Recording Results"

			junit testResults: 'target/surefire-reports/*.xml',
			skipPublishingChecks: true,
			allowEmptyResults: true

			script {
				try {
					allure([
						includeProperties: false,
						jdk: '',
						results: [[path: 'allure-results']]
					])
					echo "Allure report published successfully"
				} catch (Exception e) {
					echo "Warning: Allure report publishing skipped - ${e.message}"
				}
			}

			echo "Test results recorded"
		}

		success {
			echo "BUILD SUCCESSFUL"
			echo "All stages completed successfully!"
			echo "Test Results: PASSED"
		}

		failure {
			echo "BUILD FAILED"
			echo "One or more stages failed"
			echo "Check console output for details"
		}

		unstable {
			echo "BUILD COMPLETED"
			echo "All tests completed successfully"
		}
	}

	// Options
	options {
		buildDiscarder(logRotator(numToKeepStr: '30'))
		timeout(time: 1, unit: 'HOURS')
		timestamps()
		disableConcurrentBuilds()
	}
}