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
@Table(name = "keyword", catalog = "creawechatsys")
public class KeyWord implements java.io.Serializable {

	// Fields

	private String id;           //ID
	private String keyWord;      //关键字
	private Integer status = 1;  //状态,0禁用1启用

	// Constructors

	/** default constructor */
	public KeyWord() {
	}

	/** full constructor */
	public KeyWord(String id, String keyWord, Integer status) {
		this.id = id;
		this.keyWord = keyWord;
		this.status = status;
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

	@Column(name = "keyWord", length = 255)
	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}