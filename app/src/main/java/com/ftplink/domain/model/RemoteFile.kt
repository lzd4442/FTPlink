package com.ftplink.domain.model

import java.io.File

data class RemoteFile(
    val name: String,
    val path: String,
    val isDirectory: Boolean,
    val size: Long,
    val lastModified: Long,
    val permissions: String = ""
) {
    val extension: String
        get() = name.substringAfterLast('.', "")

    val isPreviewable: Boolean
        get() = extension.lowercase() in PREVIEWABLE_EXTENSIONS

    companion object {
        val PREVIEWABLE_EXTENSIONS = setOf(
            "jpg", "jpeg", "png", "gif", "bmp", "webp",
            "mp4", "avi", "mkv", "mov", "3gp",
            "mp3", "wav", "ogg", "flac",
            "pdf", "txt", "log", "json", "xml", "html", "css", "js", "kt", "java",
            "zip", "tar", "gz", "rar"
        )
    }
}
