// Non-deterministic Finite Automaton
import java.util.HashSet;

public class NFA extends FiniteAutomaton{

    private static final char EPSILON = 'e';
    private HashSet<Transition> delta;

    public NFA(HashSet<String> states, HashSet<Character> alphabet, HashSet<Transition> delta, String startState, HashSet<String> acceptStates) {
        super(states, alphabet, startState, acceptStates);
        this.delta = new HashSet<>();
        validateDelta();
    }

    private void validateDelta() {
        for (Transition t : delta) {
            if (!states.contains(t.getFromState()) || !states.contains(t.getToState())) {
                throw new IllegalArgumentException("Transition contains states not in the states set");
            }
            if (t.getSymbol() != EPSILON && !alphabet.contains(t.getSymbol())) {
                throw new IllegalArgumentException("Transition contains symbol not in the alphabet");
            }
        }
    }


    @Override
    public boolean accept(String input) {
        return false;
    }

}
