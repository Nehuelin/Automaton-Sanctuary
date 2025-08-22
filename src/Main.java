import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        HashSet<Character> alphabet = new HashSet<>();

        alphabet.add('0');
        alphabet.add('1');

        System.out.println(alphabet.toString());

        HashSet<String> states = new HashSet<>();
        states.add("q0");
        states.add("q1");
        states.add("q2");
        states.add("q3");

        for(String state : states) {
            System.out.println(state);
        }

        HashSet<String> acceptStates = new HashSet<>();
        acceptStates.add("q0");

        Map<String, Map<Character, String>> transitions = new HashMap<>();

        Map<Character, String> q0transitions = new HashMap<>();
        q0transitions.put('0', "q3");
        q0transitions.put('1', "q1");

        Map<Character, String> q1transitions = new HashMap<>();
        q1transitions.put('0', "q3");
        q1transitions.put('1', "q2");

        Map<Character, String> q2transitions = new HashMap<>();
        q2transitions.put('0', "q3");
        q2transitions.put('1', "q0");

        Map<Character, String> q3transitions = new HashMap<>();
        q3transitions.put('0', "q0");
        q3transitions.put('1', "q3");

        transitions.put("q0", q0transitions);
        transitions.put("q1", q1transitions);
        transitions.put("q2", q2transitions);
        transitions.put("q3", q3transitions);

        DFA automaton = new DFA(states, alphabet, transitions, "q0", acceptStates);

        System.out.println(automaton.accept("100111"));
        System.out.println(automaton.accept("01110"));

        System.out.println(automaton.getTransitionFunction().toString());
    }
}
