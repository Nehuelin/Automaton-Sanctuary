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
        return acceptHelper(startState, input, 0);
    }

    private boolean acceptHelper(String currentState, String input, int position){

        if(position == input.length()){
            HashSet<String> reachableStates = getEpsilonClosure(currentState);
            return reachableStates.stream().anyMatch(s -> acceptStates.contains(s));
        }

        char symbol = input.charAt(position);

        if(!alphabet.contains(symbol)){
            return false;
        }

        HashSet<String> epsilonStates = getEpsilonClosure(currentState);

        for(String state : epsilonStates){
            HashSet<String> nextStates = getTransitions(state, symbol);

            for(String nextState : nextStates){
                if(acceptHelper(nextState, input, position + 1)){
                    return true;
                };

            }
        }


        return false;
    }

    private HashSet<String> getEpsilonClosure(String state){
        HashSet<String> closure = new HashSet<>();
        computeEpsilonClosure(state, closure);
        return closure;
    }

    private void computeEpsilonClosure(String state, HashSet<String> visited){
        if (!visited.add(state)){
            return;
        }

        for (Transition t : delta) {
            if (t.getFromState().equals(state) && t.getSymbol() == EPSILON) {
                computeEpsilonClosure(t.getToState(), visited);
            }
        }
    }

    private HashSet<String> getTransitions(String state, char symbol){
        HashSet<String> nextStates = new HashSet<>();
        for(Transition t : delta){
            if(t.getFromState().equals(state) && t.getSymbol() == symbol){
                nextStates.add(t.getToState());
            }
        }
        return nextStates;
    }

    public DFA transformToDFA(){
        return null;
    }
}
