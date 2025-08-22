import java.util.HashSet;

public abstract class FiniteAutomaton {
    protected HashSet<String> states;
    protected HashSet<Character> alphabet;
    protected String startState;
    protected HashSet<String> acceptStates;

    public FiniteAutomaton(HashSet<String> states, HashSet<Character> alphabet, String startState, HashSet<String> acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.startState = startState;
        this.acceptStates = acceptStates;
        validate();
    }

    protected void validate() {
        if (!states.contains(startState)) {
            throw new IllegalArgumentException("Start state must be in states set");
        }
        if (!states.containsAll(acceptStates)) {
            throw new IllegalArgumentException("Accept states must be in states set");
        }
    }

    public abstract boolean accept(String input);

    public HashSet<String> getStates() {
        return states;
    }

    public void setStates(HashSet<String> states) {
        this.states = states;
    }

    public HashSet<Character> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(HashSet<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public HashSet<String> getAcceptStates() {
        return acceptStates;
    }

    public void setAcceptStates(HashSet<String> acceptStates) {
        this.acceptStates = acceptStates;
    }
}