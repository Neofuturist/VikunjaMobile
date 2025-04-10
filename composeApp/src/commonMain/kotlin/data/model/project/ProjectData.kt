package data.model.project

import kotlinx.serialization.Serializable

@Serializable
data class ProjectData(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val identifier: String? = null,
    val hex_color: String? = null,
    val parent_project_id: Int? = null,
    val owner: Owner? = null,
    val is_archived: Boolean? = null,
    val is_favorite: Boolean? = null,
    val position: Double? = null,
    val max_right: Int? = null,
    val created: String? = null,
    val updated: String? = null
)

@Serializable
data class Owner(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val created: String? = null,
    val updated: String? = null
)