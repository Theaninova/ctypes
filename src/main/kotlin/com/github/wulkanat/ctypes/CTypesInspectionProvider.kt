package com.github.wulkanat.ctypes

import com.github.wulkanat.ctypes.inspections.TypeInspection
import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool

class CTypesInspectionProvider : InspectionToolProvider {
    override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> = arrayOf(
        TypeInspection::class.java,
    )
}
