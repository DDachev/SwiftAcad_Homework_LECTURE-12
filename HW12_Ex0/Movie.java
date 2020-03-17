package bg.swift.HW12_Ex0;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Serializable, Comparable<Movie>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String director;
	private String[] actors;
	private LocalDate releaseDate;
	
	public Movie(String title, String director, LocalDate releaseDate, String...actors) {
		this.title = title;
		this.director = director;
		this.releaseDate = releaseDate;
		this.actors = actors;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String[] getActors() {
		return actors;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@Override
	public int compareTo(Movie other) {
		if (this.title.equals(other.title) && this.director.equals(other.director)
				&& this.releaseDate.equals(other.releaseDate)) {
			return 0;
		}
		return -1;
	}
	
	public void printInfo(Movie movie1, Movie movie2) {
		if(movie1.compareTo(movie2) == 0) {
			System.out.println("The movies are equals.");
		} else {
			System.out.println("The movies are not equals.");
		}
	}
}
