DzFuzzyMarkovNode {
	var < state;
	var < nextStates;
	var < weights;

	*new {
		arg state = Event[], nextStates = [], weights = [];
		^ super.new().init(state, nextStates, weights);
	}

	init {
		arg a_state, a_nextStates, a_weights;
		this.state_(a_state).nextStates_(a_nextStates).weights_(a_weights);
		^ this;
	}

	state_ {
		arg a_state;
		state = a_state;
		^ this;
	}

	nextStates_ {
		arg a_nextStates;
		nextStates = a_nextStates.asList;
		^ this;
	}

	weights_ {
		arg a_weights;
		^ this;
	}

	addNextState {
		arg a_state;
		nextStates.add(a_state);
	}
}
