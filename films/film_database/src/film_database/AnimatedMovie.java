package film_database;

import java.util.ArrayList;

public class AnimatedMovie extends Movie {
    private int rating;
    private int recommendedAge;
    private ArrayList<String> animators;

    public AnimatedMovie(String title, String director, int yearOfRelease, ArrayList<String> animators, int rating, int recommendedAge) {
        super(title, director, yearOfRelease);
        this.animators = animators;
        this.rating = rating;
        this.recommendedAge = recommendedAge;
    }
    
    public ArrayList<String> getAnimators() {
        return animators;
    }

    public void setAnimators(ArrayList<String> animators) {
        this.animators = animators;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

}

