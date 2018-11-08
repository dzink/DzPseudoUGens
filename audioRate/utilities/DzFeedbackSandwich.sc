/**
 * Wraps a function in a feedback loop. The first argument passed into the
 * function is the result of the function in the previous control block. The
 * result of the function is also passed out of the loop.
 * It should be noted that the feedback is delayed by one full control block.
 */

DzFeedbackSandwich : UGen {
	*ar {
		arg func, channels = 1;
		var signal, buffer;

		buffer = LocalBuf(BlockSize.ir, channels).clear;
		signal = PlayBuf.ar(channels, buffer, loop: 1);

		// Perform an action on the signal.
		signal = func.(signal);

		if (signal.size < channels) {
			signal.asArray.wrapExtend(channels);
		};

		RecordBuf.ar(signal, buffer, run: 1, loop: 1);
		^ signal;
	}
}
