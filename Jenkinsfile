pipeline {
	agent any

	// Define tools versions
	tools {
		maven 'Maven 3.9'
		jdk 'Java 21'
	}

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
		// Poll SCM every hour
		pollSCM('H * * * *')

		// Trigger on GitHub push (if webhook configured)
		githubPush()
	}

	// Pipeline stages
	stages {
		stage('Checkout Code') {
			steps {
				echo "Stage: Checkout Code"
				echo "Repository: ${GIT_REPO}"
				echo "Branch: ${BRANCH}"

				checkout([
					$class: 'GitSCM',
					branches: [[name: '*/${BRANCH}']],
					userRemoteConfigs: [[url: '${GIT_REPO}']]
				])

				echo "Code checkout successful"
			}
		}

		stage('Build Project') {
			steps {
				echo "Stage: Build Project"
				echo "Building with Maven..."

				sh '''
                    mvn clean compile \
                    -DskipTests \
                    -T 4 \
                    -U
                '''

				echo "Build successful"
			}
		}

		stage('Run Automation Tests') {
			steps {
				echo "Stage: Run Automation Tests"
				echo "Running 40+ test cases..."
				echo "Using 4 parallel threads"

				sh '''
                    mvn test \
                    -T 4 \
                    -DskipITs=false \
                    -Dtest=**/*Test.java
                '''

				echo "Tests execution completed"
			}
		}

		stage('Generate Allure Report') {
			steps {
				echo "Stage: Generate Allure Report"
				echo "Generating detailed Allure report..."

				sh '''
                    mvn allure:report \
                    -Dmaven.test.skip=true
                '''

				echo "Allure report generated successfully"
			}
		}

		stage('Archive Artifacts') {
			steps {
				echo "Stage: Archive Artifacts"

				sh '''
                    echo "Archiving test reports and screenshots..."
                    mkdir -p build-artifacts
                    cp -r target/surefire-reports build-artifacts/ || true
                    cp -r allure-results build-artifacts/ || true
                    cp -r allure-report build-artifacts/ || true
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

			// Publish JUnit test results
			junit testResults: '${TEST_RESULTS_DIR}/*.xml',
			skipPublishingChecks: true,
			allowEmptyResults: true

			// Publish Allure report
			publishAllure results: [
				[path: '${ALLURE_RESULTS_DIR}']
			]

			echo "Test results recorded"
		}

		success {
			echo "BUILD SUCCESSFUL"
			echo "All stages completed successfully!"
			echo "Allure Report available"
			echo "Test Results: PASSED"
		}

		failure {
			echo "BUILD FAILED"
			echo "One or more stages failed"
			echo "Check console output for details"
			echo "Review test results and logs"
		}

		unstable {
			echo "BUILD UNSTABLE"
			echo "Some tests failed"
			echo "Review Allure Report for details"
		}
	}

	// Options
	options {
		// Keep last 30 builds
		buildDiscarder(logRotator(numToKeepStr: '30'))

		// Timeout after 1 hour
		timeout(time: 1, unit: 'HOURS')

		// Add timestamps to console output
		timestamps()

		// Disable concurrent builds
		disableConcurrentBuilds()
	}
}