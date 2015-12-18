package com.crea.www.commons.util;

import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.util.DigestUtils;

/**
 * @author hp
 * @description 转义html标签
 */
public class HtmlCode {

	public static String htmldecode(String html) {
		html = html.replace("&amp;", "&");
		html = html.replace("&lt;", "<");
		html = html.replace("&gt;", ">");
		html = html.replace("&mt;", "#");
		html = html.replace("&ft;", "'");

		return html;
	}

	public static String htmlencode(String html) {

		html = html.replace("&", "&amp;");
		html = html.replace("<", "&lt;");
		html = html.replace(">", "&gt;");
		html = html.replace("#", "&mt;");
		html = html.replace("'", "&ft;");

		return html;
	}


}
