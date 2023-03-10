package cp213;

import java.io.PrintStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author David Brown
 * @version 2021-09-24
 */
public class FoodUtilities {

    /**
     * Determines the average calories in a list of foods. No rounding necessary.
     * Foods list parameter may be empty.
     *
     * @param foods a list of Food
     * @return average calories in all Food objects
     */
    public static int averageCalories(final ArrayList<Food> foods) {

	// your code here
    	int avg = 0;
		int count =0;
		
		for (Food i: foods) {
			avg += i.getCalories();
			count++;
		}
		
		avg /= count;
		
		return avg;
    }

    /**
     * Determines the average calories in a list of foods for a particular origin.
     * No rounding necessary. Foods list parameter may be empty.
     *
     * @param foods  a list of Food
     * @param origin the origin of the Food
     * @return average calories for all Foods of the specified origin
     */
    public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {

	// your code here
    	int avg = 0;
		int count = 0;
		for (Food i: foods) {
			if (i.getOrigin()==origin) {
				avg += i.getCalories();
				count++;
			}
		}
		avg /= count;
		return avg;
    }

    /**
     * Creates a list of foods by origin.
     *
     * @param foods  a list of Food
     * @param origin a food origin
     * @return a list of Food from origin
     */
    public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

	// your code here
    	ArrayList<Food> originFoods = new ArrayList<Food>();
    	
		for (Food i: foods) {
			if (i.getOrigin()==origin) {
				originFoods.add(i);
			}
		}
		return originFoods;
    }

    /**
     * Creates a Food object by requesting data from a user. Uses the format:
     *
     * <pre>
    Name: name
    Origins
     0 Canadian
     1 Chinese
    ...
    11 English
    Origin: origin number
    Vegetarian (Y/N): Y/N
    Calories: calories
     * </pre>
     *
     * @param keyboard a keyboard Scanner
     * @return a Food object
     */
    public static Food getFood(final Scanner keyboard) {

	// your code here
    	String name ="";
		int origin =0;
		boolean veg = false;
		int calories=0;
		
		
		System.out.println("Name: ");
		name = keyboard.next();
		System.out.println("Origin: ");
		origin = keyboard.nextInt();
		System.out.println("Vegetarian (Y/N): ");
		
		if (keyboard.next().equalsIgnoreCase("Y")) {
			veg = true;
		}
		System.out.println("Calories: ");
		calories = keyboard.nextInt();

		Food newFood = new Food(name,origin,veg,calories);

		return newFood;

    }

    /**
     * Creates a list of vegetarian foods.
     *
     * @param foods a list of Food
     * @return a list of vegetarian Food
     */
    public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {

	ArrayList<Food> vegFoods = new ArrayList<Food>();
	for (Food i: foods) {
		if (i.isVegetarian()) {
			vegFoods.add(i);
		}
	}
	return vegFoods;
    }

    /**
     * Creates and returns a Food object from a line of string data.
     *
     * @param line a vertical bar-delimited line of food data in the format
     *             name|origin|isVegetarian|calories
     * @return the data from line as a Food object
     */
    public static Food readFood(final String line) {

    	String[] data = line.split("\\|");

		String name = data[0];
		
		int origin = Integer.parseInt(data[1]);
		boolean veg = Boolean.parseBoolean(data[2]);
		int calories = Integer.parseInt(data[3]);

		Food newFood = new Food(name,origin
				,veg,calories);
		
		return newFood;
    }

    /**
     * Reads a file of food strings into a list of Food objects.
     *
     * @param fileIn a Scanner of a Food data file in the format
     *               name|origin|isVegetarian|calories
     * @return a list of Food
     */
    public static ArrayList<Food> readFoods(final Scanner fileIn){

	// your code her
    	ArrayList<Food> foodList = new ArrayList<Food>();

		while (fileIn.hasNextLine()) {
			foodList.add(readFood(fileIn.nextLine()));
		}
		
		return foodList;
		
    }

    /**
     * Searches for foods that fit certain conditions.
     *
     * @param foods        a list of Food
     * @param origin       the origin of the food; if -1, accept any origin
     * @param maxCalories  the maximum calories for the food; if 0, accept any
     * @param isVegetarian whether the food is vegetarian or not; if false accept
     *                     any
     * @return a list of foods that fit the conditions specified
     */
    public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories,
	    final boolean isVegetarian) {

    	ArrayList <Food> newFoods = new ArrayList<Food>();
		for (Food i:foods) {
			if ((i.getOrigin() == origin || origin == -1) &&
					(i.getCalories() <= maxCalories || maxCalories == 0) &&
					(i.isVegetarian() || isVegetarian == false)){
				newFoods.add(i);
			}
		}
		return newFoods;
    }

    /**
     * Writes the contents of a list of Food to a PrintStream.
     *
     * @param foods a list of Food
     * @param ps    the PrintStream to write to
     */
    public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

	// your code here
    	for (final Food food : foods) {
			food.write(ps);
		}

    }
}
