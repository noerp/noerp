package org.noerp.gradle.task

import org.gradle.api.DefaultTask

class StopTask extends DefaultTask {


	StopTask(){
		description = "Stop noerp application."
		group = "NoERP"
	}

}