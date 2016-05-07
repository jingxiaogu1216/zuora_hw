package config;

/**
 * Created by jingxiaogu on 5/6/16.
 */
public class Config {
    private static String database = "zuora";
    private static String ownerCollection = "owner";
    private static String requirementCollection = "requirement";

    private static String clientUrl = "mongodb://jingxiao:jingxiao@ds015942.mlab.com:15942/zuora";

    public static String getDatabase() {
        return database;
    }

    public static String getOwnerCollection() {
        return ownerCollection;
    }

    public static String getRequirementCollection() {
        return requirementCollection;
    }

    public static String getClientUrl() {
        return clientUrl;
    }
}
