package regularlanguages;

import java.util.HashSet;
import java.util.Set;

import transitions.Transition;

public class NFA extends FiniteAutomaton {

    private static final char EPSILON = 'e';

    public NFA(){}

    public NFA(HashSet<String> states, HashSet<Character> alphabet, HashSet<Transition> transitions, String startState, HashSet<String> acceptStates) {
        super(states, alphabet, transitions, startState, acceptStates);
    }

    /**
     *
     * Determines whether this NFA accepts the given input string.
     *
     *
     * The NFA explores all possible state transitions, including epsilon
     * transitions. The input is accepted if at least one computation path
     * ends in an accepting state after consuming the entire string.
     *
     * @param input the input string to be evaluated by the NFA
     * @return true if the NFA accepts the input string; false otherwise
     */
    @Override
    public boolean accept(String input) {
        Set<String> currentStates = epsilonClosure(Set.of(startState));
        System.out.println("Starting in state " + startState + "...");
        System.out.println("NFA detected initial reachable states: " + currentStates);

        for (char symbol : input.toCharArray()) {
            if (!alphabet.contains(symbol)) {
                throw new IllegalArgumentException("Input contains symbols not included in the NFA alphabet: " + symbol);
            }

            Set<String> nextStates = new HashSet<>();
            for (String state : currentStates) {
                nextStates.addAll(move(state, symbol));
            }

            System.out.println("NFA reads " + symbol + " from input. Moving from states " + currentStates + " to states " + nextStates + ".");
            currentStates = epsilonClosure(nextStates);
            if(currentStates.isEmpty()) {
                System.out.println("NFA has no reachable states after consuming the input symbol " + symbol + ". Rejecting input.");
                return false;
            }
        }

        for (String state : currentStates) {
            if (acceptStates.contains(state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all states reachable from the given set of states via epsilon transitions.
     *
     * @param states A set of states to compute the epsilon closure for.
     * @return A set of states reachable via epsilon from the given states.
     */
    private Set<String> epsilonClosure(Set<String> states) {
        Set<String> closure = new HashSet<>(states);
        Set<String> stack = new HashSet<>(states);

        while (!stack.isEmpty()) {
            String state = stack.iterator().next();
            stack.remove(state);

            for (Transition transition : transitions) {
                if (transition.getFromState().equals(state) && transition.getSymbol() == EPSILON) {
                    if (!closure.contains(transition.getToState())) {
                        closure.add(transition.getToState());
                        stack.add(transition.getToState());
                    }
                }
            }
        }
        return closure;
    }

    /**
     * Finds all states reachable from a given state via the given input symbol.
     *
     * @param state  The state to transition from.
     * @param symbol The input symbol.
     * @return A set of states reachable from the given state via the input symbol.
     */
    private Set<String> move(String state, char symbol) {
        Set<String> result = new HashSet<>();

        for (Transition transition : transitions) {
            if (transition.getFromState().equals(state) && transition.getSymbol() == symbol) {
                result.add(transition.getToState());
            }
        }
        return result;
    }

    public DFA turnToDFA() {
        return null; // To be implemented
    }
}