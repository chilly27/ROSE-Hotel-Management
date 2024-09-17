package Controller;

import Constant.GoogleConstant;
import Dal.CustomerDB;
import Model.Customer;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/callbackGoogle")
public class GoogleCallbackServlet extends HttpServlet implements GoogleConstant {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            try {
                GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JacksonFactory.getDefaultInstance(),
                        CLIENT_ID,
                        CLIENT_SECRET,
                        Collections.singleton(AUTHORIZATION_SCOPE))
                        .setDataStoreFactory(new MemoryDataStoreFactory())
                        .setAccessType("offline")
                        .build();

                GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
                String userId = "defaultUser";
                Credential credential = flow.createAndStoreCredential(tokenResponse, userId);

                Oauth2 oauth2 = new Oauth2.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                        .setApplicationName("PetShop")
                        .build();

                Userinfo userInfo = oauth2.userinfo().get().execute();
                String googleUserId = userInfo.getId();
                CustomerDB customerDB = new CustomerDB();
                if (!customerDB.checkAccountGoogle(googleUserId)) {
                    String name = userInfo.getName();
                    String pictureUrl = userInfo.getPicture();
                    String email = userInfo.getEmail();
                    customerDB.registerGoogle(name, pictureUrl, googleUserId, email);
                }
                Customer user = customerDB.loginGoogle(googleUserId);
//                if (user.isBan()){
//                    resp.sendRedirect("/login.jsp");
//                    return;
//                }
                req.getSession().setAttribute("account", user);
                resp.sendRedirect("/");
            } catch (GeneralSecurityException e) {
                System.out.println(e);
                throw new ServletException("Unable to complete Google OAuth process", e);
            }
        } else {
            resp.getWriter().write("No authorization code provided");
        }
    }
}