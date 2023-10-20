import java.util.Scanner;

class Fraction {
    private int numerator;
    private int denominator;

    /** Constructor for a Fraction.
     * @param numerator Represents the numerator 
     * @param denominator Represents the denominator
     */
    Fraction(int numerator, int denominator) throws IllegalArgumentException{
        // this refers to the Fraction so this.numerator that means this fraction's numerator

        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero"); 
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    /** Constructor for a mixed Fraction
     * @param whole Represents the whole number
     * @param numerator Represents the numerator
     * @param denominator Represents the denominator
     * @throws IllegalArgumentException Throws an Exception if the denominator is zero
     */
    Fraction(int whole, int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }

    /** Constructor for a whole number
     * @param whole Represents a whole number
     */
    Fraction(int whole){
        numerator = whole;
        denominator = 1;
    }

    /** Method for finding the greatest common denominator
     * @param n Takes a value for n as an Integer
     * @param m Takes a value for m as an Integer
     * @return Returns the greatest common denominator
     */
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

    /** Method for finding the simplified numerator
     * @param numerator Takes a value for the numerator 
     * @param denominator Takes a value for the denominator
     * @return Returns the simplified numerator
     * @throws IllegalArgumentException Throws an Exception if the denominator is zero
     */
    static int getSimplifiedNumerator(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0 ){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (numerator * denominator < 0){
            return -1 * Math.abs(numerator) / gcd(numerator, denominator);
        }
        return Math.abs(numerator) / gcd(numerator, denominator);
    }

    /** Method for finding the simplified denominator
     * @param numerator Takes in the numerator
     * @param denominator Takes in the denominator
     * @return Returns the simplified denominator
     */
    static int getSimplifiedDenominator(int numerator, int denominator){
        return Math.abs(denominator) / gcd(numerator, denominator);
    }

    // A getter is a method that returns the value of a attribute;

    // Add docStrings to all of the getters and setters

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

    /** Adds two fractions together 
     * @param other Represents the second Fraction
     * @return Returns the result of adding two fractions
     */
    Fraction add(Fraction other){
        // a/b + c/d
        // = (a * d) + ( b * c ) / (b * d)
        int commonDenominator = denominator * other.denominator; // commonDen
        int numeratorOfResult = (numerator * other.denominator) + (other.numerator * denominator);

        return new Fraction(numeratorOfResult, commonDenominator);
    }

    /** Subtracts two fractions
     * @param other Represents the second fraction
     * @return Returns the result of subtracting two fractions
     */
    Fraction subtract(Fraction other){
        
        return this.add(new Fraction(-1 * other.numerator, other.denominator));
    }

    Fraction multiply(Fraction other){
        int newNumerator = (numerator * other.numerator);
        int newDenominator = (denominator * other.denominator);

        return new Fraction(newNumerator, newDenominator);
    }

    Fraction divide(Fraction other){
        return this.multiply(new Fraction(other.denominator, other.numerator));
    }

    /** Converts the fraction from a String to an Integer
     * @param expression Takes in an expression as a String
     * @return Returns the expression, converted from a String to an Integer
     */
    static Fraction valueOf(String expression){

        int whole = 0;
        int numerator = 1;
        int denominator = 1;
        int d = expression.indexOf("/");

        if(expression.contains(" ")){
            int i = expression.indexOf(" ");
            whole = Integer.valueOf(expression.substring(0, i));

            numerator = Integer.valueOf(expression.substring(i + 1, d));
        }
        else{
            numerator = Integer.valueOf(expression.substring(0, d));
        }
        denominator = Integer.valueOf(expression.substring(d + 1));

        if(whole != 0){
            return new Fraction(whole, numerator, denominator);
        }
        return new Fraction(numerator, denominator);
    }

    static void testAdd(){
        Fraction f = new Fraction(-3, 4);
        Fraction g = new Fraction(1, 4, 5);
        System.out.println(f.add(g)); //  1 1/20
    }

    static void testSubtract(){
        Fraction f = new Fraction(8, 20);
        Fraction h = new Fraction(7, 15);
        System.out.println(f.subtract(h));
    }

    static void testMultiply(){
        Fraction f = new Fraction(2, 3, 4);
        Fraction h = new Fraction(5, 10);
        System.out.println(f.multiply(h));
    }

    static void testDivide(){
        Fraction f = new Fraction(5, 1, 4);
        Fraction h = new Fraction(3, 1, 2);
        System.out.println(f.divide(h));
    }

    static void testValueOf(){
        Fraction f = valueOf("1 2/7");
        System.out.println(f);
    }


    public static void main(String[] args) {
        // System.out.println(getSimplifiedNumerator(0, 0));

        testAdd();
        testSubtract();
        testMultiply();
        testDivide();
        testValueOf();
        

        Scanner input = new Scanner(System.in);

        try{
            String userInput = input.nextLine();
            //int indexOfSlash = userInput.indexOf("/");
            //if(userInput.charAt(indexOfSlash + 1) != ' '){
                //indexOfSlash = userInput.indexOf("/", indexOfSlash + 1);
            //}

            int indexAsterisk = -1;
            int indexAdd = -1;
            int indexSubtract = -1;
            int indexDivide = -1;
            int indexSlash = -1;
            String denominator = "";
            String numerator = "";
            String digits = "0123456789";
            String whole = "";

            for(int i = 0; i < userInput.length(); i++){
                if(userInput.charAt(i) == '*'){
                    indexAsterisk = i;
                }
                if(userInput.charAt(i) == '+'){
                    indexAdd = i;
                }
                if(userInput.charAt(i) == '-'){
                    indexSubtract = i;
                }
                if(userInput.charAt(i) == '/'){
                    indexDivide = i;
                }
            }
            System.out.println(indexDivide);
            System.out.println(indexAdd);
            System.out.println(indexAsterisk);
            System.out.println(indexSubtract);

            for(int i = indexAsterisk - 2; i >= 0; i--){
                if(userInput.charAt(i) == '/'){
                    indexSlash = i;
                    break;
                }
                denominator = denominator + userInput.charAt(i);
            }
            for(int i = indexSlash - 1; i >= 0; i--){
                if(userInput.charAt(i) == ' '){
                    if((i-1) >= 0 && digits.contains(userInput.charAt(i - 1) + "")){
                        //whole = userInput.charAt(i - 1);

                    }
                    break;
                }
                numerator = numerator + userInput.charAt(i);
            }
            System.out.println(numerator);
            System.out.println(denominator);
            Fraction f1 = new Fraction(Integer.valueOf(numerator), Integer.valueOf(denominator));
            System.out.println(f1);
            
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        
        


  

    }

}


