package com.ofbizcn.plugins.noerp

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class NoerpPlugin implements Plugin<Project> {

	void apply (Project project) {
		project.task('hello') << {
			println "hello"
		}
	}

}
