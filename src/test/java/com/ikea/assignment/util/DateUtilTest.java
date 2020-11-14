package com.ikea.assignment.util;
import com.ikea.assignment.utility.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {

    @Test
    public void convertStringToLocalDateTime() {
        Assert.assertEquals(LocalDateTime.of(2019, 04, 01, 19, 23, 54),
                DateUtil.convertStringToLocalDateTime("2019-04-01 19:23:54"));

    }
}
