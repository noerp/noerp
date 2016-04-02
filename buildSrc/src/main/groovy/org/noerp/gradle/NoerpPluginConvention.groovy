package org.noerp.gradle

import org.gradle.api.Project

class NoerpPluginConvention {
	private Project project

	public NoerpPluginConvention(Project project) {
		this.project = project
	}

	Project vendor(String notation) {

		def args = notation.split(":")
		def vendorName = args[0]
		def module = args[1]
		def version = args[2]

		return project.project(":" + vendorName + "-" + module)
	}
}