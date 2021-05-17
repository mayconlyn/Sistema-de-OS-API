package com.magiamensagem.magiasystem.dto;

import java.io.Serializable;

import com.magiamensagem.magiasystem.entities.Message;

public class MessageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cd;
	private String msg;
	
	public MessageDTO() {
	}
	
	public MessageDTO(Message entity) {
		id = entity.getId();
		cd = entity.getCd();
		msg = entity.getMsg();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
