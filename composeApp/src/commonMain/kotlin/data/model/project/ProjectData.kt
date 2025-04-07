package data.model.project

data class ProjectData(
    val background_blur_hash: String,
    val background_information: Any,
    val created: String,
    val description: String,
    val hex_color: String,
    val id: Int,
    val identifier: String,
    val is_archived: Boolean,
    val is_favorite: Boolean,
    val max_right: Int,
    val owner: Owner,
    val parent_project_id: Int,
    val position: Int,
    val subscription: Subscription,
    val title: String,
    val updated: String,
    val views: List<View>
)

data class Owner(
    val created: String,
    val email: String,
    val id: Int,
    val name: String,
    val updated: String,
    val username: String
)

data class Subscription(
    val created: String,
    val entity: Int,
    val entity_id: Int,
    val id: Int
)

data class View(
    val bucket_configuration: List<BucketConfiguration>,
    val bucket_configuration_mode: Int,
    val created: String,
    val default_bucket_id: Int,
    val done_bucket_id: Int,
    val filter: FilterX,
    val id: Int,
    val position: Int,
    val project_id: Int,
    val title: String,
    val updated: String,
    val view_kind: Int
)

data class BucketConfiguration(
    val filter: FilterX,
    val title: String
)

data class FilterX(
    val filter: String,
    val filter_include_nulls: Boolean,
    val order_by: List<String>,
    val s: String,
    val sort_by: List<String>
)