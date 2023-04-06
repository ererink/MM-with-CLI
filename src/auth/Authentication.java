package auth;

import dao.AuthDAO;
import dao.AuthDAOImpl;
import dto.ROLE;
import dto.UserDTO;
import exception.auth.LoginFailedException;
import exception.user.UserLoadFailureException;
import service.UserService;
import service.UserServiceImpl;
import session.UserSession;
import view.FailView;
import view.UserFailView;


public class Authentication {
    private static UserService userService = UserServiceImpl.getInstance();
    private static UserSession session = UserSession.getInstance();
    private static AuthDAO authDAO = AuthDAOImpl.getInstance();
    public static boolean login(String id, String pw) {
        try {
            if (authDAO.login(id, pw)) {
                UserDTO accepted = userService.userSelectOne(id).get();

                session.setUser_id(accepted.getUser_id());
                session.setRole(accepted.getRole());
                session.setClass_id(accepted.getClass_id());


                return true;
            } else {
                return false;
            }
        }
         catch (LoginFailedException e) {
            UserFailView.errorMessage(e.getMessage());
        } catch (UserLoadFailureException e) {
            UserFailView.errorMessage(e.getMessage());
        }
        return false;
    }
}
