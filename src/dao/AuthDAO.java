package dao;

import exception.auth.LoginFailedException;

public interface AuthDAO {
    boolean login(String id, String pw) throws LoginFailedException;
    
}
