package com.fsse2212_v2.eshop.util;



import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUser(JwtAuthenticationToken jwtToken) {
        return new FirebaseUserData(jwtToken);
    }
}
