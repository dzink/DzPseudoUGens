/**
 * If any trigger comes in (even if another is still active), will send another
 * trigger. Use this to have multiple options for sources that should all
 * send a trigger.
 */

DzTriggerCollector {
	*kr {
		arg ... triggers;
		var collector;
		collector = Trig1.kr(triggers, ControlDur.ir).sum;
		^ collector;
	}
}
