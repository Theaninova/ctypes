package com.github.wulkanat.ctypes.inspections

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.cidr.lang.inspections.OCInspections
import com.jetbrains.cidr.lang.psi.OCTypeElement
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor

class TypeInspection : OCInspections.GeneralCpp() {
    override fun worksWithClangd() = true
    override fun isEnabledByDefault() = true
    override fun getShortName() = "WarnLegacyTypes"
    override fun getDisplayName() = "Warn when using legacy types"
    override fun getGroupPath() = arrayOf("C/C++", "Improved Types")
    override fun getDefaultLevel(): HighlightDisplayLevel = HighlightDisplayLevel.WARNING
    override fun getStaticDescription() = "Legacy types like float, int, uint32_t are not very readable and should" +
        " be replaced with f16, i32 and u32"

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : OCVisitor() {
            override fun visitTypeElement(typeElement: OCTypeElement?) {
                val raw = typeElement?.text ?: return

                val newType = "(ui|i)nt(\\d+)_t".toRegex().matchEntire(raw)?.let {
                    val (type, bits) = it.destructured
                    "${type.first()}$bits"
                } ?: "(float|double)".toRegex().matchEntire(raw)?.let {
                    val (type) = it.destructured
                    if (type == "float") "f32" else "f64"
                } ?: "int".toRegex().matchEntire(raw)?.let {
                    "i32"
                }

                newType?.let {
                    holder.registerProblem(
                        typeElement,
                        "$raw should be replaced with $newType",
                        /*ReplaceMacroQuickFix(newType)*/
                    )
                }
            }
        }
    }
}
