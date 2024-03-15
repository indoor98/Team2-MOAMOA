package team2.proto.service;

public interface JWTBlacklistService {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
