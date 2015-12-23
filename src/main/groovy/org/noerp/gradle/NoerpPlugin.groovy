package org.noerp.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

import org.noerp.gradle.task.*

class NoerpPlugin implements Plugin<Project> {

	void apply (Project project) {

		init(project)
	
		project.task("install", type: InstallTask) {
			
		}

		project.task("update", type: UpdateTask){

		}

		project.task("start", type: StartTask){
		}

		project.task("start-debug", type: StartTask){
			debug = true
		}

		project.task("stop", type: StopTask){
			
		}

		/*
		project.task("create-component", type: CreateComponentTask){
			
		}
		project.task("generate-CRUD", type: CreateComponentTask){
			
		}
		*/
	}

	void init(Project project){

		project.defaultTasks 'build'

		project.apply plugin: 'eclipse'

		project.allprojects {
		    repositories {
		    	mavenCentral()
		        jcenter()
		    }
		}

		project.subprojects.each {
			it.apply plugin: 'java'
    		it.apply plugin: 'groovy'
		}

		project.configurations {
	    	noerp {
				description = "NoERP configurations"
	    	}
    	}
	}

}
