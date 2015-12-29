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
@Table(name = "keylink", catalog = "rendaweixin")
public class KeyLink implements java.io.Serializable {

	// Fields

	private String id;           //ID
	private String keyWordId;    //关键字ID
	private String massageId;    //消息ID

	// Constructors

	/** default constructor */
	public KeyLink() {
	}

	/** full constructor */
	public KeyLink(String id, String keyWordId, String massageId) {
		this.id = id;
		this.keyWordId = keyWordId;
		this.massageId = massageId;
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

	@Column(name = "keyWordId", length = 255)
	public String getKeyWordId() {
		return this.keyWordId;
	}

	public void setKeyWordId(String keyWordId) {
		this.keyWordId = keyWordId;
	}

	@Column(name = "massageId", length = 255)
	public String getMassageId() {
		return this.massageId;
	}

	public void setMassageId(String massageId) {
		this.massageId = massageId;
	}

}