package budget;

public class TypeToSum implements Comparable<TypeToSum>{
    String type;
    double sum;

    public TypeToSum(String type, double sum) {
        this.type = type;
        this.sum = sum;
    }

    @Override
    public int compareTo(TypeToSum typeToSum) {
        if (sum > typeToSum.sum) return -1;
        if (sum < typeToSum.sum) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return type + " - $" + String.format("%.2f", sum);
    }
}
