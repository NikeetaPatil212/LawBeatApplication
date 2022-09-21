package com.example.lawbeatapplication.dataclass

import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("title") val title: String? = null,
    @SerializedName("body") val body: String? = null,
    @SerializedName("reading_time") val readingTime: String,
    @SerializedName("source_field") val sourceField: String? = null,
    @SerializedName("synopsis") val synopsis: String? = null,
    @SerializedName("Date") val fieldDate: String? = null,
    @SerializedName("Raw_Date") val rawDate: String? = null,
    @SerializedName("Image") val fieldNewsImage: String? = null,
    @SerializedName("nid") val nid: Int? = null,
    @SerializedName("author_name") val fieldAuthorName: String? = null,
    @SerializedName("author_info") val authorInfo: List<AuthorInfoResponse>? = null,
    @SerializedName("files") val fieldFiles: String? = null,
    @SerializedName("view_node") val viewNode: String? = null,
    @SerializedName("author_logo") val author_logo: String? = null,
    @SerializedName("premium") val premium: Int? = null,
    @SerializedName("category") val category: Int? = null,
    @SerializedName("category_name") val categoryName: String? = null,
    @SerializedName("bookmark") val bookmark: Int? = null,
    @SerializedName("pinstory") val pinStory: Int? = null,
    @SerializedName("is_event") var isEvent: Int? = null,
    @SerializedName("event_link") var eventLink: String? = null,
    @SerializedName("video_url") var videoUrl: String? = null,
    @SerializedName("file_button_title") val file_button_title: String? = null,
    @SerializedName("other_images") val other_images: ArrayList<String>? = null
)
