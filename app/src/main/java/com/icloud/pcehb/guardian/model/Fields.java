package com.icloud.pcehb.guardian.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//The article contents/data

public class Fields implements Serializable {

    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("standfirst")
    @Expose
    private String standfirst;
    @SerializedName("trailText")
    @Expose
    private String trailText;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("wordcount")
    @Expose
    private String wordcount;
    @SerializedName("commentCloseDate")
    @Expose
    private String commentCloseDate;
    @SerializedName("commentable")
    @Expose
    private String commentable;
    @SerializedName("firstPublicationDate")
    @Expose
    private String firstPublicationDate;
    @SerializedName("isInappropriateForSponsorship")
    @Expose
    private String isInappropriateForSponsorship;
    @SerializedName("isPremoderated")
    @Expose
    private String isPremoderated;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("liveBloggingNow")
    @Expose
    private String liveBloggingNow;
    @SerializedName("productionOffice")
    @Expose
    private String productionOffice;
    @SerializedName("publication")
    @Expose
    private String publication;
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("shouldHideAdverts")
    @Expose
    private String shouldHideAdverts;
    @SerializedName("showInRelatedContent")
    @Expose
    private String showInRelatedContent;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("legallySensitive")
    @Expose
    private String legallySensitive;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("bodyText")
    @Expose
    private String bodyText;
    @SerializedName("charCount")
    @Expose
    private String charCount;
    @SerializedName("shouldHideReaderRevenue")
    @Expose
    private String shouldHideReaderRevenue;
    @SerializedName("showAffiliateLinks")
    @Expose
    private String showAffiliateLinks;
    @SerializedName("bylineHtml")
    @Expose
    private String bylineHtml;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getStandfirst() {
        return standfirst;
    }

    public void setStandfirst(String standfirst) {
        this.standfirst = standfirst;
    }

    public String getTrailText() {
        return trailText;
    }

    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWordcount() {
        return wordcount;
    }

    public void setWordcount(String wordcount) {
        this.wordcount = wordcount;
    }

    public String getCommentCloseDate() {
        return commentCloseDate;
    }

    public void setCommentCloseDate(String commentCloseDate) {
        this.commentCloseDate = commentCloseDate;
    }

    public String getCommentable() {
        return commentable;
    }

    public void setCommentable(String commentable) {
        this.commentable = commentable;
    }

    public String getFirstPublicationDate() {
        return firstPublicationDate;
    }

    public void setFirstPublicationDate(String firstPublicationDate) {
        this.firstPublicationDate = firstPublicationDate;
    }

    public String getIsInappropriateForSponsorship() {
        return isInappropriateForSponsorship;
    }

    public void setIsInappropriateForSponsorship(String isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = isInappropriateForSponsorship;
    }

    public String getIsPremoderated() {
        return isPremoderated;
    }

    public void setIsPremoderated(String isPremoderated) {
        this.isPremoderated = isPremoderated;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLiveBloggingNow() {
        return liveBloggingNow;
    }

    public void setLiveBloggingNow(String liveBloggingNow) {
        this.liveBloggingNow = liveBloggingNow;
    }

    public String getProductionOffice() {
        return productionOffice;
    }

    public void setProductionOffice(String productionOffice) {
        this.productionOffice = productionOffice;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShouldHideAdverts() {
        return shouldHideAdverts;
    }

    public void setShouldHideAdverts(String shouldHideAdverts) {
        this.shouldHideAdverts = shouldHideAdverts;
    }

    public String getShowInRelatedContent() {
        return showInRelatedContent;
    }

    public void setShowInRelatedContent(String showInRelatedContent) {
        this.showInRelatedContent = showInRelatedContent;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLegallySensitive() {
        return legallySensitive;
    }

    public void setLegallySensitive(String legallySensitive) {
        this.legallySensitive = legallySensitive;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getCharCount() {
        return charCount;
    }

    public void setCharCount(String charCount) {
        this.charCount = charCount;
    }

    public String getShouldHideReaderRevenue() {
        return shouldHideReaderRevenue;
    }

    public void setShouldHideReaderRevenue(String shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = shouldHideReaderRevenue;
    }

    public String getShowAffiliateLinks() {
        return showAffiliateLinks;
    }

    public void setShowAffiliateLinks(String showAffiliateLinks) {
        this.showAffiliateLinks = showAffiliateLinks;
    }

    public String getBylineHtml() {
        return bylineHtml;
    }

    public void setBylineHtml(String bylineHtml) {
        this.bylineHtml = bylineHtml;
    }

}