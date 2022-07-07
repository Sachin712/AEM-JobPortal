package com.aem.project.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFile {
	private String name;
	private String url;
	private String type;
	private long size;

	public ResponseFile(String name, String url) {
		this.name = name;
		this.url = url;
	}
	

}
