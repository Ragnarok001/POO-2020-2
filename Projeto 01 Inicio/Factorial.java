class Factorial{

    static long factorial(int n){
        if(n == 1 || n == 0) return 1;
        return n*factorial(n-1);
    }
    public static void main(String[] args){
        for(int i = 1; i <= 20; i++){
            long fat = 1;
            for(int j = i; j > 0; j--) fat *= j;
            System.out.println(factorial(i) + " " + fat);
        }
    }
}