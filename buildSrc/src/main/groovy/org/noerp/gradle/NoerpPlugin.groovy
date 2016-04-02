package org.noerp.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Action;
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.tasks.bundling.Jar
import org.noerp.gradle.task.*

class NoerpPlugin implements Plugin<Project> {

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

			subproject.apply(plugin:JavaPlugin)
			subproject.apply(plugin:GroovyPlugin)

    		subproject.convention.plugins.noerp = new NoerpPluginConvention(subproject)
			
			subproject.configurations {
				runlib {
					description = "NoERP copy libs for run"
					transitive = false
				}
				compile {
					extendsFrom runlib
				}
			}
			
			subproject.tasks.withType(Jar.class, new Action<Jar>(){
				void execute(Jar jar){
					jar.doLast {
						subproject.copy {
							from subproject.configurations.runlib.copy().files{(it instanceof ModuleDependency)}.flatten().unique()
							into "lib"
						}
					}
				}
			})
		}
	}

	void setupTask(Project project){
		project.task("install", type: InstallTask) {
		}
		project.task("update", type: UpdateTask){
		}
		project.task("start", type:StartTask){
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
			
		}*/
	}

}
