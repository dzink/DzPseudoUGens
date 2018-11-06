DzNyquist {
	*ir {
		^ SampleRate.ir * 0.5;
	}

	*period {
		^ DzNyquist.ir().reciprocal;
	}
}
