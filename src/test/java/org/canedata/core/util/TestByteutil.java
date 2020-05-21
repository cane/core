package org.canedata.core.util;

import org.junit.Test;

/**
 * @author yitao
 * Created at 2019-06-28 - 00:00
 */
public class TestByteutil {
    @Test
    public void testIsNumber(){
        assert ByteUtil.isNumber("-1");
        assert ByteUtil.isNumber("-123");
        assert ByteUtil.isNumber("+1");
        assert ByteUtil.isNumber("1");
        assert ByteUtil.isNumber("12345");
    }
}
