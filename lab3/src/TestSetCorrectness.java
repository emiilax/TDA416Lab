import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TestSetCorrectness {

    private static int n1 = 0;
    private static int n2 = 0;
    private static int n3 = 0;
    private static int n4 = 0;

    public static void main(String[] args) {

        //check if the arguments given are valid
        try {
            n1 = Integer.parseInt(args[0]);
            n2 = Integer.parseInt(args[1]);
            n3 = Integer.parseInt(args[2]);
            n4 = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal arguments");
            return;
        }

        //loops through the test n2-times
        for (int j = 0; j < n2; j++) {

            SimpleSet<Integer> simpleSet;
            Set<Integer> javaSet;
            if (n1 == 1) {
                simpleSet = new SortedLinkedListSet<Integer>();
                javaSet = new LinkedHashSet<Integer>();
            } else if (n1 == 2) {
                simpleSet = new SplayTreeSet<Integer>();
                javaSet = new TreeSet<Integer>();
            } else {
                System.out.println("n1 is an invalid number");
                return;
            }

            Random randomGen = new Random();

            //tests different operations n3 times
            for (int i = 0; i < n3; i++) {
                int randomOperation = randomGen.nextInt(4);
                int operationValue = randomGen.nextInt(n4);
                switch (randomOperation) {
                    case 0:
                        //System.out.println("Testing size.");
                        if (simpleSet.size() != javaSet.size()) {
                            System.out.println("Error occured during size()!");
                            System.out.println("simpleSet.size(): " + simpleSet.size());
                            System.out.println("javaSet.size()  : " + javaSet.size());
                            return;
                        }
                        break;
                    case 1:
                        //System.out.println("Testing add " + operationValue);
                        boolean addSimpleset = simpleSet.add(operationValue);
                        boolean addJavaSet = javaSet.add(operationValue);
                        if ( addSimpleset != addJavaSet) {
                            System.out.println("Error occured during add("+operationValue+")");
                            System.out.println("simpleSet.add() returned: " + addSimpleset);
                            System.out.println("javaSet.add() returned  : " + addJavaSet);
                            return;
                        }
                        break;
                    case 2:
                        //System.out.println("Testing contains " + operationValue);
                        boolean containsSimpleSet = simpleSet.contains(operationValue);
                        boolean containsJavaSet = javaSet.contains(operationValue);
                        if ( containsSimpleSet != containsJavaSet) {
                            System.out.println("Error occured during contains("+operationValue+")");
                            System.out.println("simpleSet.contains() returned: " + containsSimpleSet);
                            System.out.println("javaSet.contains() returned  : " + containsJavaSet);
                            return;
                        }
                        break;
                    case 3:
                        //System.out.println("Testing remove "+ operationValue);
                        boolean removeSimpleSet = simpleSet.remove(operationValue);
                        boolean removeJavaSet = javaSet.remove(operationValue);
                        if (removeSimpleSet != removeJavaSet) {
                            System.out.println("Error occured during remove("+operationValue+")");
                            System.out.println("simpleSet.remove() returned : " + removeSimpleSet);
                            System.out.println("javaSet.remove() returened  : " + removeJavaSet);
                            return;
                        }
                        break;
                }

            }
        }
        if (n1 == 1) {
            System.out.println("SortedLinkedListSet passed all tests");
        } else if (n1 == 2) {
            System.out.println("SplayTreeSet passed all tests");
        }

    }
}

