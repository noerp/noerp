package org.noerp.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Exec

class StartTask extends DefaultTask {

	Boolean debug = false

	StartTask(){
		super()

		description = "Start noerp application from a java jar package"
		group = "NoERP"
	}

	@TaskAction
	def start(){

		Exec myExec = task("myExec", type:Exec);

		myExec.commandLine = "java -jar bin/noerp.jar"
		myExec.workingDir = "."
	}
}