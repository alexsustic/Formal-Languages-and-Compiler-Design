import java.util.*;

public class FiniteAutomata {
    private List<String> states;
    private List<String> alphabet;
    private String initialState;
    private List<String> finalStates;
    private Map<List<String>, String> transitions;

    public FiniteAutomata() {
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new HashMap<>();
        this.initialState = "";
    }

    public boolean checkIfFa(String sequence) {
        if (checkIfDeterministic()) {
            String currentState = initialState;
            for (int i = 0; i < sequence.length(); i++) {
                String constant = String.valueOf(sequence.charAt(i));
                List<String> list = new ArrayList<>();
                list.add(currentState);
                list.add(constant);

                if (transitions.containsKey(list)) {
                    currentState = transitions.get(list);
                } else {
                    return false;
                }
            }
            return isFinalState(currentState);
        }
        return false;
    }

    public void initialiseParameters() {
        ReadFromFile reader = new ReadFromFile();
        List<String> parameters = reader.readLinesFromFile("src\\fa.txt");
        this.states.addAll(Arrays.asList(parameters.get(0).split(" ")));
        this.alphabet.addAll(Arrays.asList(parameters.get(1).split(" ")));
        this.initialState = parameters.get(2);
        this.finalStates.addAll(Arrays.asList(parameters.get(3).split(" ")));
        parameters = parameters.subList(4, parameters.size());
        for (String parameter : parameters) {
            String[] elements = parameter.split(" ");
            String firstElem = elements[0];
            String secondElem = elements[1];
            String thirdElem = elements[2];
            this.transitions.put(Arrays.asList(firstElem, secondElem), thirdElem);
        }
    }

    private boolean isFinalState(String state){
        for(String finalState : finalStates){
            if(finalState.equals(state)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfDeterministic() {
        for (List<String> key : transitions.keySet()) {
            if (transitions.get(key).equals("-1")) {
                return false;
            }
        }
        return true;
    }


    public void printStates() {
        System.out.println("The list of states is: " + this.states + "\n");
    }

    public void printAlphabet() {
        System.out.println("The list of alphabet is ->" + this.alphabet + "\n");
    }

    public void printInitialState() {
        System.out.println("The initial state is -> " + this.initialState + "\n");
    }

    public void printFinalStates() {
        System.out.println("The list of final states is -> " + this.finalStates + "\n");
    }

    public void printTransitions() {
        System.out.println("The list of transitions is -> " + this.transitions + "\n");
    }

}
