package org.oracle.note.util;

import java.util.UUID;


public class GetUUID{
	public  String uuid = UUID.randomUUID().toString();
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
