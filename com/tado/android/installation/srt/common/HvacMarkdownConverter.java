package com.tado.android.installation.srt.common;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.text.Html;

public class HvacMarkdownConverter {
    private static final String BOLD_REGEX = "(\\*\\*)(.*?)\\1";
    private static final String BOLD_REPLACE_REGEX = "<b>$2</b>";
    private static final String ITALICS_REGEX = "(\\*|_)(.*?)\\1";
    private static final String ITALICS_REPLACE_REGEX = "<i>$2</i>";
    private static final String LINKS_REGEX = "\\[([^\\[]+)\\]\\(([^\\)]+)\\)";
    private static final String LINKS_REPLACE_REGEX = "<a href=\"$2\">$1</a>";

    private String replaceLinksRegex(String input) {
        return replaceRegex(input, LINKS_REGEX, LINKS_REPLACE_REGEX);
    }

    private String replaceBoldRegex(String input) {
        return replaceRegex(input, BOLD_REGEX, BOLD_REPLACE_REGEX);
    }

    private String replaceItalicsRegex(String input) {
        return replaceRegex(input, ITALICS_REGEX, ITALICS_REPLACE_REGEX);
    }

    private String replaceRegex(String input, String searchRegex, String replaceRegex) {
        return input.replaceAll(searchRegex, replaceRegex);
    }

    public CharSequence convertMarkdown(@NonNull String input) {
        if (input == null) {
            throw new IllegalArgumentException("input string in HvacMarkdownConverter shouldn't be null");
        }
        input = replaceItalicsRegex(replaceBoldRegex(replaceLinksRegex(input)));
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(input, 0);
        }
        return Html.fromHtml(input);
    }
}
