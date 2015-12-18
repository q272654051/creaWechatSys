package com.crea.www.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "log", catalog = "creawechatsys")
public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private String description;
	private String method;
	private String type;
	private String requestIp;
	private String className;
	private String exceptionCode;
	private String exceptiondetail;
	private String params;
	private Integer userid;
	private Date createdate;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(String description, String method, String type,
			String requestIp, String className, String exceptionCode,
			String exceptiondetail, String params, Integer userid,
			Date createdate) {
		this.description = description;
		this.method = method;
		this.type = type;
		this.requestIp = requestIp;
		this.className = className;
		this.exceptionCode = exceptionCode;
		this.exceptiondetail = exceptiondetail;
		this.params = params;
		this.userid = userid;
		this.createdate = createdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "method", length = 500)
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "type", length = 500)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "requestIp", length = 500)
	public String getRequestIp() {
		return this.requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	@Column(name = "className", length = 500)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "exceptionCode", length = 1000)
	public String getExceptionCode() {
		return this.exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	@Column(name = "exceptiondetail", length = 65535)
	public String getExceptiondetail() {
		return this.exceptiondetail;
	}

	public void setExceptiondetail(String exceptiondetail) {
		this.exceptiondetail = exceptiondetail;
	}

	@Column(name = "params")
	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "createdate", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}