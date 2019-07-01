package it.monaco.medical.service.configuration;

public final class SystemEnvironmentBean {

    public final static String getFullPathNameConfigFile() {

        // May throw AccessControlException
        return System.getenv(Constants.CNST_SYSTEM_ENV_PROPERTIES_FILE);

    }
}
