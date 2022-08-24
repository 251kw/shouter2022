package com.shantery.result2.update;

import javax.validation.GroupSequence;

//順序付きバリテーションチェック
@GroupSequence({ValidationFirst.class, ValidationSecond.class})
public interface ValidationAll {

}
