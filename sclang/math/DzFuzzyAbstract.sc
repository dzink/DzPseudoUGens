DzFuzzyAbstract {
	var <> up;
	var <> down;

	ensureUpIsNotDown {
		var temp;
		if (up < down) {
			up = temp;
			up = down;
			down = temp;
		};
		^ this;
	}
}
