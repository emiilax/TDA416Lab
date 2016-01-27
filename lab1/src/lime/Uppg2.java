package lime;

/**
 * Created by emilaxelsson on 20/01/16.
 */
public class Uppg2 {


    static private int seqStart = 0;
    static private int seqEnd = -1;
    /**
     * contiguous subsequence sum algorithm.
     * seqStart and seqEnd represent the actual best sequence.
     * Version 1
     */
    public static int maxSubSum1( int[] a ) {
        int maxSum = 0;
        for( int i = 0; i < a.length; i++ )
            for( int j = i; j < a.length; j++ ) {
                int thisSum = 0;
                for( int k = i; k <= j; k++ ) {
                    thisSum += a[k];
                }
                if( thisSum > maxSum ) {
                    maxSum   = thisSum;
                    seqStart = i;
                    seqEnd   = j;
                } }
        return maxSum;
    }

    public static void iterations() {
        int isum = 0;
        int jsum = 0;
        int ksum = 0;
        int ktot = 0;
        for( int i = 0; i < 5; i++ ) {
            isum += 1;
            for (int j = i; j < 5; j++) {
                jsum += 1;
                for (int k = i; k <= j; k++) {
                    ksum += 1;
                }
                System.out.println(ksum + ": ksum : " + ksum);
                ktot +=ksum;
                ksum = 0;
                /*if( thisSum > maxSum ) {
                    maxSum   = thisSum;
                    seqStart = i;
                    seqEnd   = j;
                }*/
            }
            System.out.println();
        }
        System.out.println("isum :" + isum);
        System.out.println("jsum :" + jsum);
        System.out.println("ksum :" + ktot);
    }

    public static void main(String [] args){

      /*  int [] a = {1,2,2,3,4,5};
        //System.out.println(maxSubSum1(a));
        System.out.println("start " + seqStart);
        System.out.println("end " + seqEnd);
        System.out.println();

        int []b = {1,-4,7,3,4,9};
        //System.out.println(maxSubSum1(b));
        System.out.println("start " + seqStart);
        System.out.println("end " + seqEnd);
        System.out.println();*/

        iterations();
    }
}
