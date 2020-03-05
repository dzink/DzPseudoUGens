TestDzFuzzyMath : UnitTest {
	setUp {

	}

	tearDown {

	}

	test_lin {
		var r = DzFuzzyLin(1.2);
		this.assert(r.matches(2, 2.3), "DzFuzzyLin matches values above.");
		this.assert(r.matches(2, 2.5).not, "DzFuzzyLin does not match values way above.");
		this.assert(r.matches(2, 1.8), "DzFuzzyLin matches values below.");
		this.assert(r.matches(2, 0.5).not, "DzFuzzyLin does not match values way below.");
	}

	test_abs {
		var r = DzFuzzyAbs(1);
		this.assert(r.matches(2, 2.3), "DzFuzzyAbs matches values above.");
		this.assert(r.matches(2, 4).not, "DzFuzzyAbs does not match values way above.");
		this.assert(r.matches(2, 1.2), "DzFuzzyAbs matches values below.");
		this.assert(r.matches(2, 0.5).not, "DzFuzzyAbs does not match values way below.");
	}

	test_exp {
		// var r = DzFuzzyExp(2, 2);
		// this.assert(r.matches(2, 3.9), "DzFuzzyExp matches values above.");
		// this.assert(r.matches(2, 4.1).not, "DzFuzzyExp does not match values way above.");
		// this.assert(r.matches(2, 1.1), "DzFuzzyExp matches values below.");
		// this.assert(r.matches(2, 0.9).not, "DzFuzzyExp does not match values way below.");
	}
}
