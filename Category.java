package budget;

// Categories of purchases in budget manager
public enum Category {
    FOOD(1,"Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4,"Other");

    private int id;
    private String name;
    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + ":";
    }

    static public Category returnNewCategory(String id) {
        if (id.equals("1")) return Category.FOOD;
        if (id.equals("2")) return Category.CLOTHES;
        if (id.equals("3")) return Category.ENTERTAINMENT;
        else return Category.OTHER;
    }
}
