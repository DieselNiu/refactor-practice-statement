package org.coderead;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.coderead.model.Invoice;
import org.coderead.model.Play;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 新建类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class StatementTest {

    @Test
    public void test() {
        String expected = "Statement for BigCo Hamlet: $650.00 (55 seats)\n" +
                " As You Like It: $580.00 (35 seats)\n" +
                " Othello: $500.00 (40 seats)\n" +
                " niuweizhe: $500.00 (40 seats)\n" +
                "Amount owed is $2,230.00\n" +
                "You earned 57 credits\n";
        final String plays = "{" +
                "\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"}," +
                "\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"}," +
                "\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}," +
                "\"nwz\":{\"name\":\"niuweizhe\",\"type\":\"action\"}" +
                "}";

        final String invoices = "{" +
                "\"customer\":\"BigCo\",\"performances\":[" +
                "{\"playId\":\"hamlet\",\"audience\":55}" +
                "{\"playId\":\"as-like\",\"audience\":35}" +
                "{\"playId\":\"othello\",\"audience\":40}" +
                "{\"playId\":\"nwz\",\"audience\":40}" +
                "]" +
                "}";

        TypeReference<Map<String, Play>> typeReference = new TypeReference<Map<String, Play>>(){};
        Map<String, Play> playMap = JSONObject.parseObject(plays, typeReference);
        Invoice invoice = JSONObject.parseObject(invoices, Invoice.class);
        Statement statement = new Statement(invoice, playMap);
        String result = statement.show();
        assertEquals(expected, result);
    }
}
