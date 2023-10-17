class Fraction {
    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator){
        // this refer's to the Fraction so this.numerator that means this fraction's numerator

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    Fraction(int whole, int numerator, int denominator){
        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }


    Fraction(int whole){
        numerator = whole;
        denominator = 1;
    }

    static int gcd(int n, int m){
        if (n == 0 || m == 0){
            return Math.max(Math.abs(n), Math.abs(m));
        }
        int d = Math.min(Math.abs(n), Math.abs(m));
        while (n % d != 0 || m % d != 0){
            d --;
        }
        return d;
    }

    static int getSimplifiedNumerator(int numerator, int denominator){
        if (numerator * denominator < 0){
            return -1 * Math.abs(numerator) / gcd(numerator, denominator);
        }
        return Math.abs(numerator) / gcd(numerator, denominator);
    }

    static int getSimplifiedDenominator(int numerator, int denominator){
        return Math.abs(denominator) / gcd(numerator, denominator);
    }

    // getter is a method that returns the value of a attribute;

    int getNumerator(){
        return numerator;
    }

    int getDenominator(){
        return denominator;
    }

    int getWhole(){
        return numerator / denominator;
    }

    // setters are methods that set new values for the attributes
    void setNumerator(int numeratorVal){
        numerator = numeratorVal;
    }

    void setDenominator(int denominatorVal){
        denominator = denominatorVal;
    }

    boolean equals(Fraction other){
        return numerator == other.numerator && denominator == other.denominator;
    }

    public String toString(){
        if (numerator % denominator == 0){
            return String.valueOf(getWhole());
        }
        else if (Math.abs(numerator) < Math.abs(denominator)){
            return String.format("%d/%d", numerator, denominator);
        }
        int whole = getWhole();
        int newNum = Math.abs(numerator) % Math.abs(denominator);
        return String.format("%d %d/%d", whole, newNum, denominator);
    }

    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }

    boolean lessThanZero(){
        // returns True is this is less than zero
        return numerator < 0;
    }

    boolean greaterThanZero(){
        return numerator > 0;
    }

    Fraction add(Fraction other){
        // a/b + c/d
        // = (a * d + b * c) / (b*d)

        int commonDenominator = denominator * other.denominator; // commonDen
        int numeratorOfResult = numerator * other.denominator + other.numerator * denominator;

        return new Fraction(numeratorOfResult, commonDenominator);
    }  

    Fraction subtract(Fraction other){
        return this.add(new Fraction(-1 * other.numerator, denominator));
    }

    Fraction valueOf(String expression){
        // do yourself
        return 
    }

    static void testAdd(){
        Fraction f = new Fraction(-3, 4);
        Fraction g = new Fraction(1, 4, 5);
        System.out.println(f.add(g)); // 1 1/20
    }

    public static void main(String[] args) {
        testAdd();

    }
}


