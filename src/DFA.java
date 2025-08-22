// Deterministic Finite Automaton
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DFA extends FiniteAutomaton{
    private Map<String, Map<Character, String>> transitionFunction;

    public DFA(HashSet<String> states, HashSet<Character> alphabet, Map<String, Map<Character, String>> transitionFunction, String startState, HashSet<String> acceptStates) {
        super(states, alphabet, startState, acceptStates);
        this.transitionFunction = new HashMap<>();
        for (var entry : transitionFunction.entrySet()) {
            this.transitionFunction.put(entry.getKey(), new HashMap<>(entry.getValue()));
        }

        checkTransitionFunction();
    }

    private void checkTransitionFunction() {
        if(transitionFunction.size() != states.size()) {
            if(transitionFunction.size() < states.size()) {
                throw new IllegalArgumentException("Transition function has a lower number of states than the provided states set");
            } else {
                throw new IllegalArgumentException("Transition function has a higher number of states than the provided states set");
            }
        }

        for (var entry : transitionFunction.entrySet()) {
            for (var entry2 : entry.getValue().entrySet()) {
                if (!states.contains(entry2.getValue())) {
                    throw new IllegalArgumentException("Transition function contains states not included in the provided states set");
                }
            }
        }
    }

    @Override
    public boolean accept(String input) {
        String currentState = startState;
        System.out.println("Starting in state " + currentState + "...");

        for (Character c : input.toCharArray()) {
            if (!alphabet.contains(c)) {
                throw new IllegalArgumentException("Input contains symbols not included in the DFA stored alphabet");
            }

            System.out.println("DFA reads " + c + " from input." + " Moving to state " + transitionFunction.get(currentState).get(c));
            currentState = transitionFunction.get(currentState).get(c);
        }

        if (currentState == null) {
            return false;
        }
        return acceptStates.contains(currentState);
    }

    public Map<String, Map<Character, String>> getTransitionFunction() {
        return transitionFunction;
    }

    public void setTransitionFunction(Map<String, Map<Character, String>> transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

}
