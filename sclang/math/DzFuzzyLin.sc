DzFuzzyLin : DzFuzzyAbstract {

	*new {
		arg range;
		var r = super.new();
		r.up = range;
		r.down = range.reciprocal;
		r.ensureUpIsNotDown();
		^ r;
	}

	*updown {
		arg up, down;
		var r = super.new();
		r.up = up;
		r.down = down;
		r.ensureUpIsNotDown();
		^ r;
	}

	/**
	 * Receives a target and test value. The target value is from a Markov key,
	 * and the test value is a value to compare it to.
	 */
	matches {
		arg target, test;
		var ratio = target / test;
		^ ((ratio >= down) && (ratio <= up));
	}


}
