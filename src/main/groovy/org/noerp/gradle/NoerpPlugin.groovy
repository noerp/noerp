package org.noerp.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class NoerpPlugin implements Plugin<Project> {

	void apply (Project project) {
		project.task("install", type: InstallTask) {
			
		}
		project.task("update", type: UpdateTask){

		}
		project.task("start", type: StartTask){
			
		}
		project.task("start-debug", type: StartTask){
			
		}
		project.task("stop", type: StopTask){
			
		}
		project.task("create-component", type: CreateComponentTask){
			
		}
		project.task("generate-CRUD", type: CreateComponentTask){
			
		}
	}

}
