package com.music.refresh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Constants_Base {

	public static final String db_Name = "music.db";

	public static final String db_location = "jdbc:sqlite:C:\\Users\\P3INW24\\eclipse-workspace\\Jdbc_Demo\\src\\com\\db\\"
			+ db_Name;

	public static final String table_Albums = "albums";

	public static final String column_Album_Id = "_id";

	public static final String column_Album_Name = "name";

	public static final String column_Album_Artist = "artist";

	public static final int index_Album_Id = 1;

	public static final int index_Album_Name = 2;

	public static final int index_Album_Artist = 3;

	public static final String table_Songs = "songs";

	public static final String column_Songs_Id = "_id";

	public static final String column_Song_Track = "track";

	public static final String column_Song_Title = "title";

	public static final String column_Song_album = "album";

	public static final int index_Songs_Id = 1;

	public static final int index_Songs_Track = 2;

	public static final int index_Songs_Title = 3;

	public static final int index_Songs_album = 4;

	public static final String table_Artists = "artists";

	public static final String column_Artists_id = "_id";

	public static final String column_Artists_Name = "name";

	public static final int index_artists_Id = 1;

	public static final int index_artists_Name = 2;

	public static final int order_By_None = 1;

	public static final int order_By_Asc = 2;

	public static final int order_By_Desc = 3;

	public static String query_For_Retrieve_Albums_By_Artist = "Select " + table_Albums + "." + column_Album_Name
			+ " from " + table_Albums + " inner join " + table_Artists + " on " + table_Artists + "."
			+ column_Artists_id + "=" + table_Albums + "." + column_Album_Artist +

			" where " + table_Artists + "." + column_Artists_Name + "='";

	public static String query_For_Retrieve_Albums_By_Artist_OrderBy = " order by " + table_Artists + "."
			+ column_Artists_Name + " Collate nocase ";

	public static final String table_artistlist = "artists_lists";

	public static final String column_artistlist_name = "name";

	public static final String column_artistlist_album = "album";

	public static final String column_artistlist_title = "title";

	public static final String column_artistlist_track = "track";

	public static String query_Artistslists_title = "select " + column_artistlist_album + "," + column_artistlist_title
			+ "," + column_artistlist_name + "," + column_artistlist_track + " from " + table_artistlist + " where "
			+ table_artistlist + "." + column_artistlist_title + "='";

	public static String query_prep = "Select " + column_artistlist_album + "," + column_artistlist_name + ","
			+ column_artistlist_title + "," + column_artistlist_track + " from " + table_artistlist + " where "
			+ column_artistlist_title + " = ?";

	public static final String insert_Artist = "insert into " + table_Artists + "(" + column_Artists_Name
			+ ") values (?)";

	public static final String insert_album = "insert into " + table_Albums + "(" + column_Album_Name + ","
			+ column_Album_Artist + ") values (?,?)";

	public static final String insert_Songs = "insert into " + table_Songs + "(" + column_Song_album + ","
			+ column_Song_Title + "," + column_Song_Track + ") values(?,?,?)";

	private Connection connection;

	private PreparedStatement prepStatement;

	// private PreparedStatement insertPrepArtist;

	// private PreparedStatement insertPrepSongs;

	// private PreparedStatement insertPrepAlbums;

	public static final String query_retrieve_Artist_id = "Select " + column_Artists_id + " from " + table_Artists
			+ " where" + column_Artists_Name + " = ?";

	public static final String query_retrieve_Album_id = "Select " + column_Album_Id + " from " + table_Albums
			+ " where " + column_Album_Name + " = ?";

	// private PreparedStatement query_Prep_Artist;

	// private PreparedStatement query_Prep_Albums;

	// open the JDBC connection

	public boolean open() {

		try {

			connection = DriverManager.getConnection(db_location);
			return true;

		}

		catch (Exception e) {

			System.out.println("could not connect to db " + e.getMessage());

			return false;
		}

	}

	public boolean open_PreparedStatement() {

		try {

			connection = DriverManager.getConnection(db_location);

			prepStatement = connection.prepareStatement(query_prep);

			// insertPrepArtist = connection.prepareStatement(insert_Artist,
			// Statement.RETURN_GENERATED_KEYS);

			// insertPrepAlbums = connection.prepareStatement(insert_album,
			// Statement.RETURN_GENERATED_KEYS);

			// insertPrepSongs = connection.prepareStatement(insert_Songs);

			// query_Prep_Artist = connection.prepareStatement(query_retrieve_Artist_id);

			// query_Prep_Albums = connection.prepareStatement(query_retrieve_Album_id);

			return true;

		} catch (Exception e) {

			System.out.println("could not connect to db " + e.getMessage());

			return false;
		}

	}

	// close the connection

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {

			System.out.println("could not close the connection :" + e.getMessage());

		}
	}

	// retrieve the artists name and sort in order

	public List<Artists> query_Artists(int sortOrder) {

		Statement statement = null;

		ResultSet resultSet = null;

		String s = "Select * from " + table_Artists;

		if (sortOrder != order_By_None) {

			s = s + " order by " + column_Artists_Name + " Collate  nocase ";

			if (sortOrder == order_By_Asc) {

				s = s + " asc ";
			}

			else {

				s = s + " desc ";
			}

		}

		System.out.println("Query for retrieve artist id , name by order :" + s);

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(s);

			List<Artists> artist_List = new ArrayList<>();

			while (resultSet.next()) {

				Artists artist = new Artists();

				artist.setId(resultSet.getInt(column_Artists_id));

				artist.setName(resultSet.getString(column_Artists_Name));

				artist_List.add(artist);

			}

			return artist_List;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;

		}

		finally {

			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	// retrieve the album for artist and sort the albums in order

	public List<String> query_Albums_For_Artists(String artistname, int sortorder) {

		ResultSet resultSet = null;

		Statement statement = null;

		String query_albums_For_Artist = "Select " + table_Albums + "." + column_Album_Name + " from " + table_Albums
				+ " inner join " + table_Artists + " on " + table_Artists + "." + column_Artists_id + "=" + table_Albums
				+ "." + column_Album_Artist + " where " + table_Artists + "." + column_Artists_Name + "='" + artistname
				+ "'";

		if (sortorder != order_By_None) {

			query_albums_For_Artist = query_albums_For_Artist + " order by " + table_Albums + "." + column_Album_Name
					+ " Collate nocase ";

			if (sortorder == order_By_Asc) {

				query_albums_For_Artist = query_albums_For_Artist + " asc ";

			}

			else {
				query_albums_For_Artist = query_albums_For_Artist + " desc ";
			}

		}

		System.out.println("query for retrieve albums by artist name " + query_albums_For_Artist);

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(query_albums_For_Artist);

			List<String> artist_Name_List = new ArrayList<>();

			while (resultSet.next()) {

				artist_Name_List.add(resultSet.getString(column_Album_Name));

			}

			return artist_Name_List;
		}

		catch (Exception e) {

			System.out.println(e.getMessage());

			return null;

		}

	}

	// -------------------------------------------------------------------

	public static String query_For_Album_Artist_Track =

			"Select " + table_Songs + "." + column_Song_Track + ","

					+ table_Albums + "." + column_Album_Name + "," + table_Artists + "." + column_Artists_Name

					+ " from " + table_Songs

					+ " inner join " + table_Albums + " on " + table_Albums + "." + column_Album_Id + "=" + table_Songs
					+ "." + column_Song_album

					+ " inner join " + table_Artists + " on " + table_Artists + "." + column_Artists_id + "="
					+ table_Albums + "." + column_Album_Artist

					+ " where " + table_Songs + "." + column_Song_Title + "= '";

	public static String query_For_Album_Artist_Track_OrderBy = " order by " + table_Songs + "." + column_Song_Title
			+ " collate nocase ";

	// retrieve the Album Artist Track From Table

	public List<Artist_Albums_Track> retrieve_Query_For_Albums_Artist_Tracks(String albumtitle, int sortorder) {

		ResultSet resultSet = null;

		Statement statement = null;

		String s = query_For_Album_Artist_Track + albumtitle + "'";

		if (sortorder != order_By_None) {

			if (sortorder == order_By_Asc) {

				query_For_Album_Artist_Track_OrderBy = query_For_Album_Artist_Track_OrderBy + " asc ";
			}

			else {

				query_For_Album_Artist_Track_OrderBy = query_For_Album_Artist_Track_OrderBy + " desc ";

			}

		}

		System.out.println(s + query_For_Album_Artist_Track_OrderBy);

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(s + query_For_Album_Artist_Track_OrderBy);

			List<Artist_Albums_Track> artist_Name_List = new ArrayList<>();

			while (resultSet.next()) {

				Artist_Albums_Track aat = new Artist_Albums_Track();

				aat.setTrack(resultSet.getInt("track"));

				aat.setAlbum_Name(resultSet.getString("name"));

				aat.setArtist_Name(resultSet.getString(3));

				artist_Name_List.add(aat);

			}

			return artist_Name_List;
		}

		catch (Exception e) {

			System.out.println(e.getMessage());

			return null;

		}

	}

	// retrieve the Songs with help of metadata

	public void querySongsMetaData() {

		ResultSet resultSet = null;

		Statement statement = null;

		String s = "Select * from " + table_Artists;

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(s);

			ResultSetMetaData metaData = resultSet.getMetaData();

			int count = metaData.getColumnCount();

			for (int i = 1; i <= count; i++) {

				System.out.println("column " + i + " name ---> " + metaData.getColumnName(i));

			}

		} catch (Exception e) {
		}

	}

	// retrieve the count of table

	public void getCount(String tablename) {

		ResultSet resultSet = null;

		Statement statement = null;

		String s = "select count (*) as count_no , min(_id) as minimum , " + "max(_id) as maximum from " + table_Songs;

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(s);

			int value = resultSet.getInt(1); // getInt("count_no")

			int minValue = resultSet.getInt(2); // getInt("minimum")

			int maxValue = resultSet.getInt("maximum"); // getInt("maximum")

			System.out.println("count :" + value + "->min:" + minValue + " max ->" + maxValue);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------------------->

	public static String query_View_ArtistList = "create view if not exists artists_lists as select " + table_Artists
			+ "." + column_Artists_Name + "," + table_Albums + "." + column_Album_Name + " as album " + ","
			+ table_Songs + "." + column_Song_Title + "," + table_Songs + "." + column_Song_Track + " from "
			+ table_Songs + " inner join " + table_Albums + " on " + table_Albums + "." + column_Album_Id + "="
			+ table_Songs + "." + column_Song_album + " inner join " + table_Artists + " on " + table_Artists + "."
			+ column_Artists_id + "=" + table_Albums + "." + column_Album_Artist + " order by " + table_Artists + "."
			+ column_Artists_Name + "," + table_Albums + "." + column_Album_Name + "," + table_Songs + "."
			+ column_Song_Title + "," + table_Songs + "." + column_Song_Track;

	// create a new list in existing table with help of view

	public void query_For_Create_ArtistList() {

		Statement statement = null;

		try {

			statement = connection.createStatement();

			statement.execute(query_View_ArtistList);

			System.out.println(query_View_ArtistList);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	// ------------------------------------------------------------------------------------->

	// retrieve the query artist lists and song details from Artist list table

	public List<Artist_Albums_Track> query_ArtistLists_SongDetails(String title) {

		ResultSet resultSet = null;

		Statement statement = null;

		String s = query_Artistslists_title + title + "'";

		System.out.println(s);

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery(s);

			List<Artist_Albums_Track> list = new ArrayList<>();

			while (resultSet.next()) {

				Artist_Albums_Track aat = new Artist_Albums_Track();
				aat.setArtist_Name(resultSet.getString(column_artistlist_name));
				aat.setAlbum_Name(resultSet.getString(column_artistlist_album));
				aat.setTitle(resultSet.getString(column_artistlist_title));
				aat.setTrack(resultSet.getInt(column_artistlist_track));

				list.add(aat);
			}

			return list;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			return null;
		}

	}

	// -------------------------------------------------------------------------->

	public List<Artist_Albums_Track> query_PreparedStatement(String title) {

		ResultSet resultSet;

		try {

			prepStatement.setString(1, title);
			resultSet = prepStatement.executeQuery();

			List<Artist_Albums_Track> list = new ArrayList<>();

			while (resultSet.next()) {

				Artist_Albums_Track aat = new Artist_Albums_Track();
				aat.setArtist_Name(resultSet.getString(column_artistlist_name));
				aat.setAlbum_Name(resultSet.getString(column_artistlist_album));
				aat.setTitle(resultSet.getString(column_artistlist_title));
				aat.setTrack(resultSet.getInt(column_artistlist_track));

				list.add(aat);
			}

			return list;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			return null;
		}

	}

}
