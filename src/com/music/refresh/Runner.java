package com.music.refresh;

import java.sql.SQLException;
import java.util.List;

// execution purpose

public class Runner {

	public static void main(String[] args) throws SQLException {

		Constants_Base base = new Constants_Base();

		if (!base.open()) {

			System.out.println("Cannot open the database ");

		}

		else {

			System.out.println("Connecting Database -----> ");
		}

		System.out.println("retrieve the artist id and name");

		List<Artists> query_Artists = base.query_Artists(Constants_Base.order_By_Asc);

		for (Artists artists : query_Artists) {

			System.out.println("id : " + artists.getId() + " name -> " + artists.getName());
		}

		System.out.println("End of Query");

		System.out.println("retrieve the albums by Iron Maiden artist");

		List<String> queryAlbumsForArtist = base.query_Albums_For_Artists("Iron Maiden", Constants_Base.order_By_Asc);

		for (String string : queryAlbumsForArtist) {

			System.out.println(string);

		}

		System.out.println("***** End of Query *************");

		System.out.println();

		System.out.println();

		System.out.println();

		System.out.println("Retrieve All Columns From Albums Artist Tracks class");

		List<Artist_Albums_Track> retrieve_Query_For_Albums_Artist_Tracks = base
				.retrieve_Query_For_Albums_Artist_Tracks("Heartless", Constants_Base.order_By_Asc);

		for (Artist_Albums_Track artist_Albums_Track : retrieve_Query_For_Albums_Artist_Tracks) {

			System.out.println(artist_Albums_Track.getAlbum_Name() + "-->" + artist_Albums_Track.getTrack() + "-->"
					+ artist_Albums_Track.getArtist_Name());
		}

		System.out.println("***** End of Query *************");

		System.out.println();

		System.out.println();

		System.out.println("Retrieve the Metadata");

		base.querySongsMetaData();

		System.out.println("retrieve the Count of Songs");

		base.getCount("songs");

		System.out.println("Create the Artist List");

		base.query_For_Create_ArtistList();

		System.out.println("retrieve the query artistlist Columns");

		List<Artist_Albums_Track> query_ArtistLists_SongDetails = base.query_ArtistLists_SongDetails("Heartless");

		for (Artist_Albums_Track value : query_ArtistLists_SongDetails) {

			System.out.println("Album : " + value.getAlbum_Name() + "---> title : " + value.getTitle() + "---> name : "
					+ value.getArtist_Name() + "---. track : " + value.getTrack());

		}

//		List<Artist_Albums_Track> query_PreparedStatement = base.query_PreparedStatement("Heartless");
//
//		for (Artist_Albums_Track qp : query_PreparedStatement) {
//
//			System.out.println(qp.getAlbum_Name() + qp.getTitle() + qp.getArtist_Name());
//		}
//

	}

}
