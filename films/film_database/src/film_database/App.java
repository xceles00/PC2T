package film_database;


import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
	    FilmManager manager = new FilmManager();
	    Scanner input = new Scanner(System.in);
	    int choice = 0;
	    do {
	        try {
	            System.out.println("\nMovie Management System");
	            System.out.println("1. Add new movie");
	            System.out.println("2. Edit movie");
	            System.out.println("3. Remove movie");
	            System.out.println("4. Print all movies");
	            System.out.println("5. Search movie by title");
	            System.out.println("6. Save movie information to file");
	            System.out.println("7. Exit");
	            System.out.print("Enter your choice (1-6): ");
	            choice = input.nextInt();
	            input.nextLine();
	            switch (choice) {
	                case 1:
	                    manager.addMovie();
	                    break;
	                case 2:
	                    manager.editMovie();
	                    break;
	                case 3:
	                    manager.removeMovie();
	                    break;
	                case 4:
	                    manager.printMovies();
	                    System.out.println();
	                    break;
	                case 5:
	                    manager.searchMovie();
	                    System.out.println();
	                    break;
	                case 6:
	                	manager.saveMovie();
	                	System.out.println();
	                case 7:
	                    System.out.println("... ");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter a valid choice.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid integer choice between 1 and 6.");
	            input.nextLine(); // clear the input buffer
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    } while (choice != 7);
	    input.close();
	}
	
}
