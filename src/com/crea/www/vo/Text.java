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
@Table(name = "text", catalog = "creawechatsys")
public class Text implements java.io.Serializable {

	// Fields

	private String id;           //ID
	private String content;      //内容

	// Constructors

	/** default constructor */
	public Text() {
	}

	/** full constructor */
	public Text(String id, String content, Integer isfirst, Integer isdefault) {
		this.id = id;
		this.content = content;
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

	@Column(name = "content", length = 255)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}