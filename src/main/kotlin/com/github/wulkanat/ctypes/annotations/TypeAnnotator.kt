package com.github.wulkanat.ctypes.annotations

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys
import com.jetbrains.cidr.lang.psi.impl.OCMacroReferenceElementImpl

class TypeAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is OCMacroReferenceElementImpl -> if (element.text matches "([iuf]\\d+|string)".toRegex()) {
                holder.createAnnotation(HighlightSeverity.INFORMATION, element.rangeWithMacros, "").apply {
                    textAttributes = OCHighlightingKeys.OC_KEYWORD
                }
            }
        }
    }
}
