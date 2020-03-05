DzFuzzyExp : DzFuzzyAbstract  {
	var <> exp;

	*new {
		arg range, exp = 2;
		var r = super.new();
		r.up = exp.pow(range);
		r.down = exp.pow(range.reciprocal);
		r.exp = exp;
		r.ensureUpIsNotDown();
		^ r;
	}

	*updown {
		arg up, down, exp = 2;
		var r = super.new();
		r.up = up;
		r.down = down;
		r.exp = exp;
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
		[up, down, exp,ratio].postln;
		^ ((ratio >= down) && (ratio <= up));
	}
}
