package com.catkatpowered.katserver.common.constants;

public class KatConfigNodeConstants {

    public static final String KAT_CONFIG_RESOURCE = "resource";
    public static final String KAT_CONFIG_DATA_FOLDER_PATH = KAT_CONFIG_RESOURCE + "." + "data_folder_path";

    public static final String KAT_CONFIG_NETWORK = "network";
    public static final String KAT_CONFIG_NETWORK_PORT = KAT_CONFIG_NETWORK + "." + "network_port";
    public static final String KAT_CONFIG_NETWORK_CUSTOM_CERT_ENABLED =  KAT_CONFIG_NETWORK + "." + "custom_cert" + "." + "enabled";
    public static final String KAT_CONFIG_NETWORK_CUSTOM_CERT_PATH = KAT_CONFIG_NETWORK + "." + "custom_cert" + "." + "cert_path";
    public static final String KAT_CONFIG_NETWORK_CUSTOM_CERT_PASSWORD = KAT_CONFIG_NETWORK + "." + "custom_cert" + "." + "cert_password";
    public static final String KAT_CONFIG_NETWORK_SELFGEN_CERT_PASSWORD = KAT_CONFIG_NETWORK + "." + "selfgen_cert" + "." + "cert_password";
    public static final String KAT_CONFIG_NETWORK_SELFGEN_CERT_ALIAS = KAT_CONFIG_NETWORK + "." + "selfgen_cert" + "." + "cert_alias";

    public static final String KAT_CONFIG_EXEC = "exec";
    public static final String KAT_CONFIG_EXEC_THREADS = KAT_CONFIG_EXEC + "." + "exec_threads";

    public static final String KAT_CONFIG_TOKENPOOL = "tokenpool";
    public static final String KAT_CONFIG_TOKENPOOL_OUTDATE = KAT_CONFIG_TOKENPOOL + "." + "outdate";

    public static final String KAT_CONFIG_DATABASE = "database";
    public static final String KAT_CONFIG_DATABASE_TYPE = KAT_CONFIG_DATABASE + "." + "database_type";
    public static final String KAT_CONFIG_DATABASE_URL = KAT_CONFIG_DATABASE + "." + "database_url";
    public static final String KAT_CONFIG_DATABASE_USER_NAME = KAT_CONFIG_DATABASE + "." + "database_username";
    public static final String KAT_CONFIG_DATABASE_PASSWORD = KAT_CONFIG_DATABASE + "." + "database_password";
}
