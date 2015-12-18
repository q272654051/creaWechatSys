package com.crea.www.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article", catalog = "creawechatsys")
public class Article implements java.io.Serializable {

	// Fields

	private String id;             //ID
	private String title;          //标题
	private String description;    //描述
	private String picUrl;         //图片url
	private String url;            //url

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(String id, String title, String description, String picUrl, String url, Integer isfirst, Integer isdefault) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "picUrl", length = 50)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "url", length = 50)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}