package com.example.helloarticles.model.reponse

import java.io.Serializable

data class ResponseArticles(
    val copyright: String,
    val num_results: Int,
    val results: List<Post>,
    val status: String
):Serializable
data class Post(
    val `abstract`: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any,
    val des_facet: List<String>,
    val eta_id: Int,
    val geo_facet: List<String>,
    val id: Long,
    val media: List<Media>,
    val nytdsection: String,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
):Serializable
data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    val mediametadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
):Serializable

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
):Serializable
