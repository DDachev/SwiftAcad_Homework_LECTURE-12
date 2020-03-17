package bg.swift.HW12_Ex0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Task0_SaveObjectsToFiles {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Movie movie = new Movie("The Godfather", "Francis Ford Coppola", LocalDate.of(1972, 3, 14), "Marlon Brando",
				"Al Pacino", "James Caan");
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("movie.txt"));
		output.writeObject(movie);
		output.close();
		
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("movie.txt"));
		Movie movie2 = (Movie) input.readObject();
		input.close();
		
		movie.printInfo(movie, movie2);
	}
}
