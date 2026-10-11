package com.ftplink.domain.model

data class ServerConfig(
    val id: Long = 0,
    val name: String,
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val type: ServerType,
    val lastPath: String = "/",
    val createdAt: Long = System.currentTimeMillis()
)

enum class ServerType {
    FTP,
    SFTP
}
