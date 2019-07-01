package it.monaco.medical.service.utils;

import it.monaco.medical.service.model.enums.EnumChannel;
import org.slf4j.MDC;

public class LegacyLogUtils {

    public static void setParamsForLog(String sessionId, EnumChannel channel, String host)
    {

        MDC.put("sessionId", sessionId);
        MDC.put("host", host);
        MDC.put("channel", channel != null ? channel.getChannel(): null);
    }
}
