DzFuzzyMarkovSet : List {
	var < ranges;
	var < nodes;
	var < currentState;

	*new {
		^ super.new().init();
	}

	init {
		ranges = IdentityDictionary[];
		// ranges = this.setRanges(IdentityDictionary[]);
		nodes = List[];
	}

	setRanges {
		arg a_ranges;
		ranges = a_ranges;
		^ this;
	}

	add {
		arg state, nextState;
		var index = this.indexOf(state);
		[\adding, state, nextState].postln;
		if (index.notNil) {
			nodes[index].addNextState(nextState);
		} {
			nodes.add(DzFuzzyMarkovNode(state, List[nextState]));
		};
		^ this;
	}

	addAll {
		arg state, stateList;
		if (this.at(state)) {
			nodes[state].addNextState(stateList);
		} {
			nodes.put(DzFuzzyMarkovNode(state, stateList));
		};
		^ this;
	}

	addNode {
		arg node;
		nodes.add(node);
	}

	addStream {
		arg start, stream, count = 30;
		var c = 0;
		var last = start;
		stream.do {
			arg item;
			if (last.notNil) {
				this.add(last, item);
			};
			last = item;
			c = c + 1;
			if (c >= count) {
				^ this;
			};
		};
		^ this;
	}

	next {
		var state = this.get(currentState);
		if (state.isNil) {
			state = this.get();
		};
		currentState = state[0];
		^ currentState;
	}

	get {
		arg currentState;
		var matches;
		if (currentState.isNil) {
			// [\nocurrent].postln;
			^ nodes.choose.nextStates.choose;
		};
		matches = this.getMatches(currentState);
		if (matches.size == 0) {
			// [\nomatch].postln;
			^ nodes.choose.nextStates.choose;
		};
		^ matches.choose;
	}

	getMatches {
		arg currentState;
		var matchBag = Bag[];
		nodes.do {
			arg node;
			// [\matching, node.state, currentState, this.testMatch(node.state, currentState)].postln;
			if (this.testMatch(node.state, currentState)) {
				matchBag.addAll(node.nextStates);
			};
		};
		// matchBag.postln;
		^ matchBag;
	}

	testMatch {
		arg state, anotherState;
		// [\tm, this.ranges].postln;
		this.ranges.keysValuesDo {
			arg property, range;
			// [\test, property, state, anotherState, range].postln;
			if (range.matches(state.at(property), anotherState.at(property)).not) {
				// [\notamatch].postln;
				^ false;
			};
		};
		^ true;
	}

	indexOf {
		arg state;
		nodes.do {
			arg node, index;
			if (node.state == state) {
				^ index;
			};
		};
		^ nil;
	}

	at {
		arg state;
		var index = this.indexOf(state);
		if (index.notNil) {
			^ nodes[index];
		};
		^ nil;
	}
}
