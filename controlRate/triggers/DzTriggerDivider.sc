/**
 * Use a stepper to only pass every nth trigger.
 */
DzTriggerDivider : UGen {
	*kr {
		arg trigger, reset, count = 1, offset = 0;
		var trigCount;
		trigCount = Stepper.kr(trigger, reset, 0, count - 1);

		// Because Stepper.kr always starts on 1
		offset = (offset + 1) % count;
		trigger = trigger * BinaryOpUGen('=', trigCount, offset);
	}
}
