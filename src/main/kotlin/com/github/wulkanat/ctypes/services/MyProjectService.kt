package com.github.wulkanat.ctypes.services

import com.intellij.openapi.project.Project
import com.github.wulkanat.ctypes.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
