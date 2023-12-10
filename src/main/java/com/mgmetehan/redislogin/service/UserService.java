package com.mgmetehan.redislogin.service;

import com.mgmetehan.redislogin.dto.CreateUserDto;
import com.mgmetehan.redislogin.model.User;
import com.mgmetehan.redislogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public String createUser(CreateUserDto dto) {
        userRepository.save(dto.toEntity(dto));
        return "User created";
    }

    public String loginAndGetToken(String username, String password) {
        // Check if the token is cached in Redis
        String cachedToken = getCachedToken(username);

        if (cachedToken != null) {
            return cachedToken; // Token is already cached, return it
        }

        // Actual authentication logic (check username and password) here
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Generate a random token
            String token = generateRandomToken();

            // Cache the token in Redis with a TTL (e.g., 60 minutes)
            cacheToken(username, token);

            return token; // Return the generated token
        }

        return null; // Authentication failed
    }

    private String getCachedToken(String username) {
        // Retrieve the token from Redis cache
        return redisTemplate.opsForValue().get("token:" + username);
    }

    private void cacheToken(String username, String token) {
        // Cache the token in Redis with a TTL (e.g., 60 minutes)
        redisTemplate.opsForValue().set("token:" + username, token, Duration.ofMinutes(60));
    }

    private String generateRandomToken() {
        // Generate a random UUID-based token (you can use a more secure method)
        return UUID.randomUUID().toString();
    }

    public String deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setDeleted(true);
            userRepository.save(user.get());
            return "User deleted";
        } else {
            return "User not found";
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
