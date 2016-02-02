package lime;



public class Lab2a {


  public static double[] simplifyShape(double[] poly, int k) {

    if(poly.length <= 4) return poly;

    double [] copy = poly;

    double lowestValue = -1;
    int lowValPosition = 0;

    double currentValue;

    // variable for the three lines
    double l1 = 0;
    double l2 = 0;
    double l3 = 0;

    while(copy.length/2 > k && copy.length > 4){
      lowestValue = -1;
      for(int i = 0; i < copy.length; i = i + 2){

        if((i+5) > copy.length ) break;

        double [] L = {copy[i], copy[i+1]};
        double [] P = {copy[i+2], copy[i+3]};
        double [] R = {copy[i+4], copy[i+5]};


        l1 = Math.sqrt(Math.pow(L[0]-P[0], 2) + Math.pow(L[1]-P[1], 2));
        l2 = Math.sqrt(Math.pow(P[0]-R[0], 2) + Math.pow(P[1]-R[1], 2));
        l3 = Math.sqrt(Math.pow(L[0]-R[0], 2) + Math.pow(L[1]-R[1], 2));

        currentValue = Math.abs(l1+l2-l3);

        if(lowestValue > currentValue || lowestValue == -1){
          lowestValue = currentValue;
          lowValPosition = i+2;
        }
      }

      copy = removePoint(copy, lowValPosition);
    }

    return copy;
  }

  /**
   * Returns a new list with one point (two elements) deleted.
   *
   * @param array array with values
   * @param position the position of the x-coordinate of the point in the array
   * @return
     */
  private static double [] removePoint(double [] array, int position){

    double [] newArray = new double[array.length - 2];
    System.out.println("position: " + position);
    System.out.println("new array: " + newArray.length);
    System.out.println("old array" + array.length);

    int pos = 0;

    for(int i = 0; i < array.length; i++){


      if(!(i == position || i == (position + 1))){
        System.out.println("i : " + i);
        newArray[pos] = array[i];
        System.out.println("pos : " + pos);
        pos++;
      }
    }

    return newArray;
  }


  public static void main(String[] args){
    double [] poly = {2,5,6,8,9,10,11,15, 3, 5, 6, 7};
    double [] copy = simplifyShape(poly, 3);

    for(int i = 0; i <poly.length; i++){

      System.out.println("Poly: " + poly[i]);



    }
    System.out.println();

    for(int i = 0; i <copy.length; i++){

      System.out.println("copy: " + copy[i]);


    }




  }


}
