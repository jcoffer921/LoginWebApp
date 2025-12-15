package entity;

/**
 * Domain model for a Customer.
 * <p>
 * Represents a row in the {@code customer} table and is used by the
 * servlet layer to render data and by DAOs to transfer data from/to
 * the database.
 */

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String favoriteMeal;

    /**
     * No-args constructor for frameworks and convenience.
     */
    public Customer() {}

    /**
     * Full constructor including the generated {@code id}.
     */
    public Customer(int id, String firstName, String lastName, String favoriteMeal) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteMeal = favoriteMeal;
    }

    /**
     * Constructor used before the customer has been persisted (no id yet).
     */
    public Customer(String firstName, String lastName, String favoriteMeal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteMeal = favoriteMeal;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavoriteMeal() {
        return favoriteMeal;
    }
    public void setFavoriteMeal(String favoriteMeal) {
        this.favoriteMeal = favoriteMeal;
    }
}
