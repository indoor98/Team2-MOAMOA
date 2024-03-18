package team2.proto.service.authentication;

public interface JWTBlacklistService {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
