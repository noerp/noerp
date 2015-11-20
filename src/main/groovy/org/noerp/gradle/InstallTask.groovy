import org.gradle.api.DefaultTask

class InstallTask extends DefaultTask {

	@TaskAction
	def install(){
		println "install task"
	}

}