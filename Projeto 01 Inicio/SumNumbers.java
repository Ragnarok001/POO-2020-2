class SumNumbers{
    public static void main(String[] args){
        int a1 = 1;
        int an = 1000;
        int n = 1000;
        int Sn = ((a1+an)*n)/2;
        int Sn2 = 0;
        for(int i = 1; i <= 1000; i++)Sn2 += i;
        System.out.println(Sn + " " + Sn2);
    }
}