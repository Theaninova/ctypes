package com.github.wulkanat.ctypes.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.jetbrains.cidr.lang.util.OCElementFactory

class ReplaceMacroQuickFix(private val newType: String) : LocalQuickFix {
    override fun getFamilyName() = "Replace with $newType"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        OCElementFactory.expressionFromText(newType, descriptor.startElement, true)?.let {
            descriptor.startElement.replace(it)
        }
    }
}
