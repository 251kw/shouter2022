package com.shantery.result2.update;

import javax.validation.GroupSequence;

@GroupSequence({ValidationFirst.class, ValidationSecond.class})
public interface ValidationAll {

}
