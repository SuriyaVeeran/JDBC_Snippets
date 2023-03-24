package com.music.refresh;

public class Albums {

	// maintains the columns and implement POJO

	private int id;

	private String name;

	private int artist;

	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArtist() {
		return artist;
	}

	public void setArtist(int artist) {
		this.artist = artist;
	}

}
