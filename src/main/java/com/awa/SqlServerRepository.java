package com.awa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

import java.util.List;

@Component
public class SqlServerRepository implements Repository {

    @Autowired
    DataSource dataSource;
    private Connection conn;

    @Override
    public long createUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[lunchusers] (firstname, lastname, email, nickname, state, creation_date, pass)  VALUES (?,?,?,?,?,GETDATE(),?)")) {

            ps.setString(1, user.getFirstname());

            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getNickname());
            ps.setLong(5, user.getState());


            ps.setString(6, user.getPassword());


            int rs = ps.executeUpdate();
            if (rs == 0) {

                System.out.println("error is 0");
            }

            return 1;


        } catch (SQLException e) {
            throw new LunchRepositoryException(e);
        }
    }

    @Override
    public long createGroup(UserGroup group) {
        return 0;
    }

    @Override
    public long createLunch(Lunch lunch) {
        return 0;
    }

    @Override
    public User getUser(long userID) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public UserGroup getGroup(long groupID) {
        return null;
    }

    @Override
    public List<User> getUsersInGroup(long groupID) {
        return null;
    }

    @Override
    public Lunch getLunch(long lunchID) {
        return null;
    }

    @Override
    public List<UserGroup> getGroupList(long userID) {
        return null;
    }

    @Override
    public List<Lunch> getLunchList(long userID) {
        return null;
    }

    @Override
    public List<Lunch> getPublicLunchList() {
        return null;
    }

    @Override
    public long updateUser(long userID, User user) {
        return 0;
    }

    @Override
    public long updateGroup(long groupID, UserGroup group) {
        return 0;
    }

    @Override
    public long addUserToGroup(long groupID, long userID) {
        return 0;
    }

    @Override
    public long removeUserFromGroup(long groupID, long userID) {
        return 0;
    }

    @Override
    public long updateLunch(long lunchID, Lunch lunch) {
        return 0;
    }

    @Override
    public long addUserToLunch(long lunchID, long userID) {
        return 0;
    }

    @Override
    public long removeUserFromLunch(long lunchID, long userID) {
        return 0;
    }

    @Override
    public long addGroupToLunch(long lunchID, long groupID) {
        return 0;
    }
}
