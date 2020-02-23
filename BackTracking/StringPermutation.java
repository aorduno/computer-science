package BackTracking;

public class StringPermutation {
    private void permute(String string) {
        doPermute("", string);
    }

    private void doPermute(String permutation, String remaining) {
        if (remaining.equals("") && isPrintable(permutation)) {
            System.out.println("Permutation: " + permutation);
        }

        // go char by char on our remaining and call the function recursively, to send two pieces from remaining...
        // our permutation and the remaining...
        for (int x = 0; x < remaining.length(); x++) {
            char current = remaining.charAt(x); // current char
            String before = remaining.substring(0, x); // excludes last
            String after = remaining.substring(x + 1); // includes first

            doPermute(permutation + current, before + after);
        }
    }

    // Given a string, generate all permutations of it that do not contain ‘B’ after ‘A’, i.e., the string should not
    // contain “AB” as a substring.
    private boolean isPrintable(String permutation) {
        return !permutation.toLowerCase().contains("ab");
    }

    public static void main(String[] args) {
        // in order to permute the string our algorithm needs to...
        // there needs to be some recursion...
        // our recursive function will take a letter at the time and start building the permutation
        // also need to pass what's left to permute so...
        // 1) our recursive function checks if the remaining is empty (no more chars to permute) and print it!
        // 2) if there's more to permute we take that 1 char at the time, concat to our current permutation
        // and call our recursive with alternatives
        StringPermutation permuter = new StringPermutation();
        String string = "abc";
        System.out.println("Permutations for: " + string);
        permuter.permute(string);
    }
}
