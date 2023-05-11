package com.nytimes.poc.model.schema


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Article(
  @SerializedName("abstract")
  val `abstract`: String?="",
  @SerializedName("adx_keywords")
  val adxKeywords: String?="",
  @SerializedName("asset_id")
  val assetId: Long?=0,
  @SerializedName("byline")
  val byline: String?="",
  @SerializedName("des_facet")
  val desFacet: List<String>?=null,
  @SerializedName("eta_id")
  val etaId: Int?=0,
  @SerializedName("id")
  val id: Long?=0,
  @SerializedName("media")
  val media: List<Media>?=null,
  @SerializedName("nytdsection")
  val nytdsection: String?="",
  @SerializedName("org_facet")
  val orgFacet: List<String>?,
  @SerializedName("per_facet")
  val perFacet: List<String>?=null,
  @SerializedName("published_date")
  val publishedDate: String?="",
  @SerializedName("section")
  val section: String?="",
  @SerializedName("source")
  val source: String?="",
  @SerializedName("subsection")
  val subsection: String?="",
  @SerializedName("title")
  val title: String?="",
  @SerializedName("type")
  val type: String?="",
  @SerializedName("updated")
  val updated: String?="",
  @SerializedName("uri")
  val uri: String?="",
  @SerializedName("url")
  val url: String?=""
) : Parcelable {
  @Parcelize
  data class Media(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int?=0,
    @SerializedName("caption")
    val caption: String?="",
    @SerializedName("copyright")
    val copyright: String?="",
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>?,
    @SerializedName("subtype")
    val subtype: String?="",
    @SerializedName("type")
    val type: String?
  ) : Parcelable {
    @Parcelize
    data class MediaMetadata(
      @SerializedName("format")
      val format: String?="",
      @SerializedName("height")
      val height: Int?=0,
      @SerializedName("url")
      val url: String?="",
      @SerializedName("width")
      val width: Int
    ) : Parcelable
  }
  fun getThumbnail():String?{
    return media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url
  }
}