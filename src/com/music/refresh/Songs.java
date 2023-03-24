package com.music.refresh;

public class Songs {
	
	// maintain the column and implement POJO

	private int id;

	private int track;

	private String title;

	private int album;

	public int get_id() {
		return id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAlbum() {
		return album;
	}

	public void setAlbum(int album) {
		this.album = album;
	}

}
