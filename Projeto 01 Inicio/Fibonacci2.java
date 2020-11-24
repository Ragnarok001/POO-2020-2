class Fibonacci2{
    public static void main(String[] args){
        int second = 1;
        int third = 1;
        System.out.print("0 1 1 ");
        do{
            third = third + second;
            second = third - second;
            System.out.print(third + " ");
        }
        while(third < 100);
    }
}