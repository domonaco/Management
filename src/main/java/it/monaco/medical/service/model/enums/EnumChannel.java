package it.monaco.medical.service.model.enums;

public enum EnumChannel {

    WEB_SITE("WEB_SITE"),
    MOBILE("MOBILE"),
    PHONE("PHONE"),
    EXTRA("EXTRA"),
    AGGREGATOR("AGGREGATOR"),
    GW_CC("GW_CC"),
    GW_PC("GW_PC"),
    GW_BC("GW_BC"),
    GW_CM("GW_CM"),
    AGGREGATOR_BATCH("AGGREGATOR_BATCH");

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    private String channel;
    private EnumChannel(String channel){
        this.channel=channel;
    }


}
