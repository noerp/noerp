package org.noerp.gradle.task

import org.gradle.api.DefaultTask

class UpdateTask extends DefaultTask {

	UpdateTask(){
		description = "Update noerp vendors."
		group = "NoERP"
	}

}