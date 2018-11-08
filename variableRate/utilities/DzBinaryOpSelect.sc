/**
 * Convenience class to insert() a value if a condition is true, or insert() 0 if it is not. Inserting 0 does not change an index. This is used if there are multiple points at which you would like to insert a value, and you'd like to select between them in real time.
 * In the conditional insert, neither of your test inputs are UGens. conditionalInserts are computationally expensive, and you should use an if statement in your code instead.
 */
DzBinaryOpSelect {
	*ar {
		arg operation, testA, testB, trueValue, falseValue;
		^ Select.ar(BinaryOpSelect.compare(operation, testA, testB), [falseValue, trueValue]);
	}

	*kr {
		arg operation, testA, testB, trueValue, falseValue;
		^ Select.kr(BinaryOpSelect.compare(operation, testA, testB), [falseValue, trueValue]);
	}

	*compare {
		arg operation, testA, testB;
		if (testA.isKindOf(UGen) || testB.isKindOf(UGen)).not {
			warn('Neither of your test inputs are UGens. BinaryOpSelects are computationally expensive, and you should use an if statement in your code instead.');
			^ (testA === testB).asInteger;
		};

		// @TODO do I want this?
		if (operation === '=') {
			operation = '==';
		};

		^ BinaryOpUGen(operation, testA, testB);
	}
}
