class NewX{
    public static void main(String[] args){
        int x = 13;
        while( x != 1){
            if(x%2 != 0) x = (3*x)+1;
            else x/=2;
            System.out.println(x);
        }
    }
}