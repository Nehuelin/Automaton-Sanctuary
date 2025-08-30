package regularlanguages; // Deterministic Finite Automaton
import java.util.HashSet;
import java.util.Set;
import transitions.Transition;

public class DFA extends FiniteAutomaton {

    public DFA(){}

    public DFA(HashSet<String> states, HashSet<Character> alphabet, HashSet<Transition> transitions, String startState, HashSet<String> acceptStates) {
        super(states, alphabet, transitions, startState, acceptStates);
        checkTransitionFunction();
    }

    private void checkTransitionFunction() {
        for (Transition transition : transitions) {
            if (!states.contains(transition.getFromState()) || !states.contains(transition.getToState())) {
                throw new IllegalArgumentException("Transition contains states not in the provided states set: " + transition);
            }
            if (!alphabet.contains(transition.getSymbol())) {
                throw new IllegalArgumentException("Transition contains symbols not in the provided alphabet: " + transition);
            }
        }
    }

    /**
     * Determines whether this DFA accepts the given input string.
     *
     *
     * The DFA starts in the initial state and consumes the input symbol
     * by symbol, following exactly one transition for each symbol.
     * Because the automaton is deterministic, for every state and input
     * symbol there is at most one valid transition.
     * If, after processing the entire input, the DFA ends in an accepting
     * state, the string is accepted.
     *
     * @param input the input string to be evaluated by the DFA
     * @return true if the DFA accepts the input string; false otherwise
     */
    @Override
    public boolean accept(String input) {
        String currentState = startState;
        System.out.println("Starting in state " + currentState + "...");

        for (char symbol : input.toCharArray()) {
            if (!alphabet.contains(symbol)) {
                throw new IllegalArgumentException("Input contains symbols not included in the DFA stored alphabet");
            }

            Transition matchingTransition = null;
            for (Transition transition : transitions) {
                if (transition.getFromState().equals(currentState) && transition.getSymbol().equals(symbol)) {
                    matchingTransition = transition;
                    break;
                }
            }

            if (matchingTransition == null) {
                return false;
            }

            System.out.println("DFA reads " + symbol + " from input. Moving to state " + matchingTransition.getToState());
            currentState = matchingTransition.getToState();
        }

        return acceptStates.contains(currentState);
    }

    public DFA minimizeStates() {
        return null;
    }
}