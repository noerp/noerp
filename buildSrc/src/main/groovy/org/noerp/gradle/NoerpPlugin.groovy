package org.noerp.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

import org.gradle.api.internal.file.FileResolver;
import org.gradle.internal.reflect.Instantiator;

import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.GroovyPlugin;

import org.noerp.gradle.task.*
import org.noerp.gradle.dependency.*

import javax.inject.Inject

class NoerpPlugin implements Plugin<Project> {

	private final Instantiator instantiator
    private final FileResolver fileResolver

	@Inject
	NoerpPlugin(Instantiator instantiator, FileResolver fileResolver){
		this.instantiator = instantiator
		this.fileResolver = fileResolver
	}

	void apply (Project project) {
		init(project)
		setupTask(project)
	}

	void init(Project project){

		project.defaultTasks 'build'

		project.allprojects {
		    repositories {
		    	mavenCentral()
		        jcenter()
		    }
		}

		project.subprojects.each {subproject->

			subproject.apply plugin:JavaPlugin
			subproject.apply plugin:GroovyPlugin

    		subproject.extensions.create("vendor", VendorDependency)
		}
	}

	void setupTask(Project project){
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
		project.task("create-component", type: CreateComponentTask){
			
		}
		project.task("generate-CRUD", type: CreateComponentTask){
			
		}
	}

}
