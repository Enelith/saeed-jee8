/**
 * 
 */
package academy.learnprogramming.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

import academy.learnprogramming.annotations.DebugLogged;
import academy.learnprogramming.entity.User;
import academy.learnprogramming.service.QueryService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author Jonathan Vinh
 */
@ApplicationScoped
public class SecurityUtil {
    public static final String BEARER = "Bearer";
    public static final String HASHED_PASSWORD_KEY = "hashedPassword";
    public static final String SALT_KEY = "salt";

    @Inject
    private QueryService queryService;
    
    private SecretKey securityKey;

    @PostConstruct
    private void init() {
	securityKey = generateKey();
    }

    public Date toDate(LocalDateTime localDateTime) {
	return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public boolean passwordsMatch(String storedHashedPassword, String saltText, String clearTextPassword) {
	ByteSource salt = ByteSource.Util.bytes(Hex.decode(saltText));
	String hashedPassword = hashAndSaltPassword(clearTextPassword, salt);
	return hashedPassword.equals(storedHashedPassword);
    }

    public Map<String, String> hashPassword(String clearTextPassword) {
	ByteSource salt = getSalt();

	Map<String, String> credentialMap = new HashMap<>();
	credentialMap.put(HASHED_PASSWORD_KEY, hashAndSaltPassword(clearTextPassword, salt));
	credentialMap.put(SALT_KEY, salt.toHex());

	return credentialMap;
    }

    private ByteSource getSalt() {
	return new SecureRandomNumberGenerator().nextBytes();
    }

    private String hashAndSaltPassword(String clearTextPassword, ByteSource salt) {
	return new Sha512Hash(clearTextPassword, salt, 2000000).toHex();
    }

    @DebugLogged
    public boolean authenticateUser(String email, String password) {
	User user = queryService.findUserByEmail(email);
	if (user == null) {
	    return false;
	}
	return passwordsMatch(user.getPassword(), user.getSalt(), password);
    }

    private SecretKey generateKey() {
	return MacProvider.generateKey(SignatureAlgorithm.HS512);
    }

    public SecretKey getSecurityKey() {
	return securityKey;
    }
}
