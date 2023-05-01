package film_database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FilmManager {
    
    private ArrayList<Movie> movies;
    
    private Scanner input;
    
    public FilmManager() {
        movies = new ArrayList<Movie>();
        input = new Scanner(System.in);
    }
    
    public void addMovie() {
        boolean validInput = false;
        while (!validInput) {
        	try {
                System.out.print("Type (1 for animated, 2 for action): ");
                int type = input.nextInt();
                input.nextLine();
                System.out.print("Title: ");
                String title = input.nextLine();
                System.out.print("Director: ");
                String director = input.nextLine();
                System.out.print("Year of release: ");
                int yearOfRelease = input.nextInt();
                input.nextLine();

                if (type == 1) {
                	ArrayList<String> animators = new ArrayList<String>();
                    while (true) {
                        System.out.print("Animators (enter to finish): ");
                        String animator = input.nextLine();
                        if (animator.isEmpty()) {
                            break;
                        }
                        animators.add(animator);
                    }
                    System.out.print("Rating (from 1 to 10): ");
                    int rating = input.nextInt();
                    input.nextLine();
                    System.out.print("Recommended age: ");
                    int recommendedAge = input.nextInt();
                    input.nextLine();
                    movies.add(new AnimatedMovie(title, director, yearOfRelease, animators, rating, recommendedAge));
                    System.out.println("\nMovie added.");
                    validInput = true;
                }
                else if (type == 2) {
                    ArrayList<String> actors = new ArrayList<String>();
                    while (true) {
                        System.out.print("Actors (enter to finish): ");
                        String actor = input.nextLine();
                        if (actor.isEmpty()) {
                            break;
                        }
                        actors.add(actor);
                    }
                    System.out.print("Rating (from 1 to 5): ");
                    int rating = input.nextInt();
                    input.nextLine();
                    movies.add(new ActionMovie(title, director, yearOfRelease, actors, rating));
                    System.out.println("\nMovie added.");
                    validInput = true;
                }
                else {
                    System.out.println("Invalid type");
                }
            }
        	catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
                input.nextLine();
                }
        	}
    }
    
    public void printMovies() {
			System.out.println();
    		for (Movie movie : movies) {
    			System.out.println("Movie number " + (movies.lastIndexOf(movie)+1) + ":");
    	        System.out.println("Title: " + movie.getTitle());
    	        System.out.println("Director: " + movie.getDirector());
    	        System.out.println("Year of Release: " + movie.getYearOfRelease());
    	        if (movie instanceof AnimatedMovie) {
    	            AnimatedMovie animatedMovie = (AnimatedMovie) movie;
    	            System.out.println("Animators: " + animatedMovie.getAnimators());
    	            System.out.println("Rating (from 1 to 10): " + animatedMovie.getRating());
    	            System.out.println("Recommended Age: " + animatedMovie.getRecommendedAge());
    	        } else if (movie instanceof ActionMovie) {
    	            ActionMovie actionMovie = (ActionMovie) movie;
    	            System.out.println("Actors: " + actionMovie.getActors());
    	            System.out.println("Stars (from 1 to 5): " + actionMovie.getRating());
    	        }
    	        System.out.println("---------------------");
    	    }
    }
    
    public void editMovie() {
        try {
            System.out.print("Enter the title of the movie to edit: ");
            String title = input.nextLine();
            boolean movieFound = false;
            for (Movie movie : movies) {
                if (movie.getTitle().equalsIgnoreCase(title)) {
                    movieFound = true;
                    System.out.println("Enter new parameters of the movie:");
                    System.out.print("Title: ");
                    title = input.nextLine();
                    movie.setTitle(title);
                    System.out.print("Director: ");
                    String director = input.nextLine();
                    movie.setDirector(director);
                    System.out.print("Year of release: ");
                    int year = input.nextInt();
                    input.nextLine();
                    movie.setYearOfRelease(year);
                    if (movie instanceof AnimatedMovie) {
                        AnimatedMovie animatedMovie = (AnimatedMovie) movie;
                        ArrayList<String> animators = new ArrayList<String>();
                        while (true) {
                            System.out.print("Animators (enter to finish): ");
                            String animator = input.nextLine();
                            if (animator.isEmpty()) {
                                break;
                            }
                            animators.add(animator);
                        }
                        animatedMovie.setAnimators(animators);
                        System.out.print("Rating from (1 to 10): ");
                        int rating = input.nextInt();
                        input.nextLine();
                        animatedMovie.setRating(rating);
                        System.out.print("Recommended Age: ");
                        int age = input.nextInt();
                        input.nextLine();
                        animatedMovie.setRecommendedAge(age);
                    } else if (movie instanceof ActionMovie) {
                        ActionMovie actionMovie = (ActionMovie) movie;
                        System.out.print("Actors: ");
                        ArrayList<String> actors = new ArrayList<String>();
                        while (true) {
                            System.out.print("Type actor name (or enter to finish): ");
                            String actor = input.nextLine();
                            if (actor.isEmpty()) {
                                break;
                            }
                            actors.add(actor);
                        }
                        actionMovie.setActors(actors);
                        System.out.print("Stars (from 1 to 5): ");
                        int stars = input.nextInt();
                        input.nextLine();
                        actionMovie.setRating(stars);
                    }
                    System.out.println("Movie edited. Type enter to continue.");
                    return;
                }
            }
            if (!movieFound) {
                System.out.println("Movie not found. Type enter to continue.");
            }
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            input.nextLine();
        }
    }
    
    public void removeMovie() {
    	    System.out.print("Enter the title of the movie to remove: ");
    	    String title = input.nextLine();
    	    boolean movieFound = false;
    	    for (Movie movie : movies) {
    	        if (movie.getTitle().equalsIgnoreCase(title)) {
    	            movies.remove(movie);
    	            System.out.println("Movie removed. Type enter to continue.");
    	            movieFound = true;
    	            break;
    	        }
    	    }
    	    if (!movieFound) {
    	        System.out.println("Movie not found. Type enter to continue. ");
    	    }
    	    input.nextLine(); 
    	}
    	
    public void searchMovie() {
    	System.out.print("Enter the title of the movie to search: ");
    	String title = input.nextLine();
    	System.out.println();
    	boolean found = false;
    	for (Movie movie : movies) {
    		if (movie.getTitle().equalsIgnoreCase(title)) {
    			if (movie instanceof AnimatedMovie) {
    				AnimatedMovie animatedMovie = (AnimatedMovie) movie;
            		System.out.println(animatedMovie.getTitle() + " (" + animatedMovie.getYearOfRelease() + ") " + "directed by " + animatedMovie.getDirector() + ", rating (from 1 to 10): " + animatedMovie.getRating() + ", recommended age: " + animatedMovie.getRecommendedAge() + ", animators: " + animatedMovie.getAnimators());
            		}
    			else if(movie instanceof ActionMovie) {
            		 ActionMovie actionMovie = (ActionMovie) movie;
            		 System.out.println(actionMovie.getTitle() + " (" + actionMovie.getYearOfRelease() + ") " + "directed by " + actionMovie.getDirector() + ",  stars (from 1 to 5): " + actionMovie.getRating() + ", actors: " + actionMovie.getActors());
            		 }
    			found = true;
    		}
    		if (!found) {
            System.out.println("Movie " + title + " not found.");
            }
    	}
    }
    
    public void saveMovie() {
    	System.out.print("Enter the title of the movie you want to save: ");
    	String title = input.nextLine();
    	System.out.println();
    	boolean found = false;
    	for (Movie movie : movies) {
    		if (movie.getTitle().equalsIgnoreCase(title)) {
    			if (movie instanceof AnimatedMovie) {
    				AnimatedMovie animatedMovie = (AnimatedMovie) movie;
    				try {
    					BufferedWriter writer = new BufferedWriter(new FileWriter(movie.getTitle() + ".txt"));
    					writer.write("Title: " + animatedMovie.getTitle() + "\n");
    					writer.write("Director: " + animatedMovie.getDirector() + "\n");
    					writer.write("Release year: " + animatedMovie.getYearOfRelease() + "\n");
    					writer.write("Animators: " + animatedMovie.getAnimators() + "\n");
    					writer.write("Recommended age: " + animatedMovie.getRecommendedAge() + "\n");
    					writer.write("Rating (from 1 to 10): " + animatedMovie.getRating());
    					writer.close();
    					} catch (IOException e) {
    						System.out.println("Saving movie information to file failed.");
    					}
    	        }
    			else if(movie instanceof ActionMovie) {
    				ActionMovie actionMovie = (ActionMovie) movie;
    				try {
    					BufferedWriter writer = new BufferedWriter(new FileWriter(movie.getTitle() + ".txt"));
    					writer.write("Title: " + actionMovie.getTitle() + "\n");
    					writer.write("Director: " + actionMovie.getDirector() + "\n");
    					writer.write("Release year: " + actionMovie.getYearOfRelease() + "\n");
    					writer.write("Actors: " + actionMovie.getActors() + "\n");
    					writer.write("Stars (from 1 to 5): " + actionMovie.getRating());
    					writer.close();
    					} catch (IOException e) {
    						System.out.println("Saving movie information to file failed.");
    					}
    			}
    			found = true;
    			System.out.println("Movie successfully saved to file. ");
    		}
    		if (!found) {
	            System.out.println("Movie " + title + " not found.");
			}
    	}
    }
    
    public void searchMoviesByActor() {
    	System.out.print("Enter the name: ");
    	String Name = input.nextLine();
    	System.out.println();
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie instanceof AnimatedMovie) {
                AnimatedMovie animatedMovie = (AnimatedMovie) movie;
                if (animatedMovie.getAnimators().contains(Name)) {
                    result.add(animatedMovie);
                }
            } else if (movie instanceof ActionMovie) {
                ActionMovie actionMovie = (ActionMovie) movie;
                if (actionMovie.getActors().contains(Name)) {
                    result.add(actionMovie);
                }
            }
        }
        if (result.isEmpty()) {
            System.out.println("No movies found for actor: " + Name);
        } else {
            System.out.println("Movies found: " + Name);
            for (Movie movie : result) {
                System.out.println(movie.getTitle() + " (" + movie.getYearOfRelease() + ")");
            }
        }
    }
}