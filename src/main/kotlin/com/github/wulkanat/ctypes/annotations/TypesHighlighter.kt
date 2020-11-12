package com.github.wulkanat.ctypes.annotations

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Font

object TypesHighlighter {
    val TYPES_KEYWORD = TextAttributesKey.createTextAttributesKey(
        "TYPES.KEYWORD",
        TextAttributes(
            DefaultLanguageHighlighterColors.KEYWORD.defaultAttributes.foregroundColor,
            null, null, EffectType.STRIKEOUT, Font.BOLD
        )
    )
}
