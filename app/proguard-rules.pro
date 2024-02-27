# Keep the class that contains the obfuscated API key
-keep class com.marwa.ecinterviewtask.data.datasource.remote.api.ApiKeyProvider {
    *;
}

# Keep the method that provides the obfuscated API key
-keepclassmembers class com.marwa.ecinterviewtask.data.datasource.remote.api.ApiKeyProvider {
    public static java.lang.String getApiKey();
}
