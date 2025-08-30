package transitions;

public class Transition {
    private String fromState;
    private Character symbol;
    private String toState;

    public Transition(String fromState, Character symbol, String toState) {
        this.fromState = fromState;
        this.symbol = symbol;
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "Transition{" + "fromState=" + fromState + ", symbol=" + symbol + ", toState=" + toState + '}';
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }
}
