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

    public static String doOperation(char op, String userInput, int indexOp){
        String digits = "0123456789";
        int start = 0;
        int end = 0;
        String whole = "";

        String fractionLeft = "";

        for(int i = indexOp - 2; i >= 0; i--){
            if(userInput.charAt(i) == ' '){
                i--;
                while((i) >= 0 && digits.contains(userInput.charAt(i) + "")){
                    whole = userInput.charAt(i) + whole;
                    i--;
                }

                if(i > -1){
                    start = i + 2;
                }

                else{
                    start = i + 1;
                }

                break;
            }
            fractionLeft = userInput.charAt(i) + fractionLeft;
        }

        if(! whole.equals("")){
            fractionLeft = whole + " " + fractionLeft;
        }
        System.out.println(fractionLeft);
        Fraction f1 = valueOf(fractionLeft);
      //  System.out.println("f1 = " + f1);

        String fractionRight = "";
        whole = "";

        for(int i = indexOp + 2; i < userInput.length();){

            if(userInput.charAt(i)!= ' ')
                fractionRight = fractionRight + userInput.charAt(i);

            if((i + 1) == userInput.length()){
                end= i+1;
                break;
            }
            if((i + 1) < userInput.length() && userInput.charAt(i) == ' ' && digits.contains(userInput.charAt(i + 1) + "")){
                whole = fractionRight;
                fractionRight = "";

            }

            if((i + 1) < userInput.length() && userInput.charAt(i) == ' ' &&  !digits.contains(userInput.charAt(i + 1) + "")){

                end = i;
                break;
            }
            i++;
        }
        fractionRight= fractionRight.replace(" ", "");
        if(! whole.equals("")){
            fractionRight = whole + " " + fractionRight;
        }

        System.out.println(fractionRight);
        Fraction f2 = valueOf(fractionRight);

        System.out.println(f1);
        System.out.println(f2);
        Fraction result = solve(f1, f2, op);
        //System.out.println("result= " + result);

        if(start!= 0 || end != userInput.length()){
            return userInput.substring(0, start) + result + userInput.substring(end);
        }
        else{
            return result.toString();
        }

    }

    public static int getIndex(char op, String userInput){
        int index = -1;

        for(int i = 0; i < userInput.length(); i++){
            if(userInput.charAt(i) == op){
                if(userInput.charAt(i) == '/' && userInput.charAt(i + 1) == ' '){
                    index = i;
                }
                if(op != '/') {
                    index = i;
                }
            }
        }
        return index;
    }

    static Fraction solve(Fraction f1, Fraction f2, char op){
        if (op == '/')
            return f1.divide(f2);
        if (op =='*')
            return f1.multiply(f2);
        if (op == '+')
            return f1.add(f2);
        else
            return f1.subtract(f2);
    }

    static void testAdd(){
        Fraction f = new Fraction(-3, 4);
        Fraction g = new Fraction(1, 4, 5);
        //System.out.println(f.add(g)); //  1 1/20
    }

    static void testSubtract(){
        Fraction f = new Fraction(8, 20);
        Fraction h = new Fraction(7, 15);
        //System.out.println(f.subtract(h));
    }

    static void testMultiply(){
        Fraction f = new Fraction(2, 3, 4);
        Fraction h = new Fraction(5, 10);
        //System.out.println(f.multiply(h));
    }

    static void testDivide(){
        Fraction f = new Fraction(5, 1, 4);
        Fraction h = new Fraction(3, 1, 2);
        //System.out.println(f.divide(h));
    }

    static void testValueOf(){
        Fraction f = valueOf("1 2/7");
        //System.out.println(f);
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

            while(getIndex('/', userInput) > -1){
                userInput = doOperation('/', userInput, getIndex('/', userInput));
            }

            System.out.println(userInput);

            while(getIndex('*', userInput) > -1){
                userInput = doOperation('*', userInput, getIndex('*', userInput));
            }
            System.out.println(userInput);

            while(getIndex('+', userInput) > -1){
                userInput = doOperation('+', userInput, getIndex('+', userInput));
            }
            System.out.println(userInput);



            while(getIndex('-', userInput)> -1)
                userInput = doOperation('-', userInput, getIndex('-', userInput));
            System.out.println(userInput);

        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }






    }

}


