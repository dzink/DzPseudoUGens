DzSpreadAz : UGen {
	* ar {
		arg numChans = 2, in, spread = 1, pos = 0, orientation = 0, center = false;
		var audio = 0, base, inSize, width;
		inSize = in.asArray.size.asInt;
		width = 2 * max(spread, 1);
		spread = spread * (inSize - 1)/numChans;
		if (center.not) {
			pos = pos + spread;
		};

		^ SplayAz.ar(numChans, in, spread, 1, width, pos, orientation, false);
	}
}
