class Fibonacci{

    static int fibonacci(int n){
        if(n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
    public static void main(String[] args){
        int first = 1;
        int second = 1;
        int third = 1;
        int i = 3;
        System.out.print("0 0\n1 1\n1 1\n");
        do{
            first = second;
            second = third;
            third = first + second;
            System.out.println(third + " " + fibonacci(i));
            i++;
        }
        while(third < 100);
    }
}