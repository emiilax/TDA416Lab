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
                        System.out.println("Testing size.");
                        if (simpleSet.size() != javaSet.size()) {
                            System.out.println("Error occured during size()!");
                            return;
                        }
                        break;
                    case 1:
                        System.out.println("Testing add " + operationValue);
                        if (simpleSet.add(operationValue) != javaSet.add(operationValue)) {
                            System.out.println("Error occured during add("+operationValue+")");
                            return;
                        }
                        break;
                    case 2:
                        System.out.println("Testing contains " + operationValue);
                        if (simpleSet.contains(operationValue) != javaSet.contains(operationValue)) {
                            System.out.println("Error occured during contains("+operationValue+")");
                            return;
                        }
                        break;
                    case 3:
                        System.out.println("Testing remove "+ operationValue);
                        if (simpleSet.remove(operationValue) != javaSet.remove(operationValue)) {
                            System.out.println("Error occured during remove("+operationValue+")");
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

