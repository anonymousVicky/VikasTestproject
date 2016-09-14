/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

public class Random {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public int randomInt() {

        java.util.Random rand = new java.util.Random();
        int num = rand.nextInt(9000000) + 1000000;
        // System.out.println(num);  

        return num;
    }

    public int randomusername() {

        java.util.Random rand = new java.util.Random();
        int num = rand.nextInt(9000000) + 1000000;
        // System.out.println(num);  

        return num;
    }

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
