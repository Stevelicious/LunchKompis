package com.awa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SqlServerRepository implements Repository {

    @Autowired
    DataSource dataSource;
    private Connection conn;

    @Override
    public long createUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(
             		"INSERT INTO [dbo].[users] (firstname, lastname, email, username, password)  VALUES (?,?,?,?,?); " +
			                "INSERT INTO [dbo].[userRoles] (user_id, role) VALUES ((SELECT userid FROM [users] where username =?),?);")) {

            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getNickname());
            ps.setString(5, user.getPassword());
	        ps.setString(6,user.getNickname() );
	        ps.setString(7, "USER");

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
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[Usergroup] (title, creation_date, is_perm)  VALUES (?,GETDATE(),?)")) {

            ps.setString(1, group.getTitle());

            ps.setBoolean(2, group.isPerm());



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
    public long createLunch(Lunch lunch) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[lunch] (title, creation_date, date, time, isPublic, place)  VALUES (?,GETDATE(),?, ?,?, ? )")) {

            ps.setString(1, lunch.getTitle());

            ps.setDate(2, Date.valueOf(lunch.getDate()));
            ps.setTime(3, Time.valueOf(lunch.getTime()));
            ps.setBoolean(4, lunch.isPublic());
            ps.setString(5, lunch.getPlace());

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
    public User getUser(long userID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[lunchusers] WHERE Userid=? ")) {
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("pass"));
                user.setCreation_date(rs.getDate("creation_date"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getInt("state"));


            }
            return user;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }



    }

    @Override
    public List<User> getUsers() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[lunchusers] ")) {

            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("pass"));
                user.setCreation_date(rs.getDate("creation_date"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getInt("state"));
                userList.add(user);



            }
            return userList;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }

    }

    @Override
    public UserGroup getGroup(long groupID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[Usergroup] WHERE usergroupid=? ")) {
            ps.setLong(1, groupID);
            ResultSet rs = ps.executeQuery();
            UserGroup group = new UserGroup();
            while (rs.next()) {
                group.setTitle(rs.getString("title"));
                group.setCreationdate(LocalDateTime.from(rs.getDate("creation_date").toLocalDate()));
                group.setPerm(rs.getBoolean("isPerm"));



            }
            return group;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }
    }

    @Override
    public List<User> getUsersInGroup(long groupID) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.lunchusers WHERE Userid IN(SELECT User_id FROM dbo.UserConnector WHERE usergroup_id = ?); ")) {
            ps.setLong(1, groupID);
            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("pass"));
                user.setCreation_date(rs.getDate("creation_date"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getInt("state"));
                userList.add(user);



            }
            return userList;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }





    }

    @Override
    public Lunch getLunch(long lunchID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[lunch] WHERE Lunchid=? ")) {
            ps.setLong(1, lunchID);
            ResultSet rs = ps.executeQuery();
            Lunch lunch = new Lunch();
            while (rs.next()) {
                lunch.setTitle(rs.getString("title"));
                lunch.setDate(rs.getDate("date").toLocalDate());
                lunch.setTime(rs.getTime("time").toLocalTime());
                lunch.setPublic(rs.getBoolean("isPublic"));
                lunch.setPlace(rs.getString("place"));



            }
            return lunch;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }



    }

    @Override
    public List<UserGroup> getGroupList(long userID) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.UserGroup WHERE usergroupid IN(SELECT usergroupid FROM dbo.UserConnector WHERE User_id = ?); ")) {
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            List<UserGroup> groupList = new ArrayList<UserGroup>();
            while (rs.next()) {
                UserGroup group = new UserGroup();
                group.setTitle(rs.getString("title"));
                group.setCreationdate(LocalDateTime.from(rs.getDate("creation_date").toLocalDate()));
                group.setPerm(rs.getBoolean("isPerm"));
                groupList.add(group);



            }
            return groupList;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }



    }

    @Override
    public List<Lunch> getLunchList(long userID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.lunch WHERE Lunchid IN(SELECT Lunch_id FROM dbo.Connector WHERE User_id = ?); ")) {
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            List<Lunch> lunchList = new ArrayList<Lunch>();
            while (rs.next()) {
                Lunch lunch = new Lunch();
                lunch.setTitle(rs.getString("title"));
                lunch.setDate(rs.getDate("date").toLocalDate());
                lunch.setTime(rs.getTime("time").toLocalTime());
                lunch.setPublic(rs.getBoolean("isPublic"));
                lunch.setPlace(rs.getString("place"));
                lunchList.add(lunch);



            }
            return lunchList;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }




    }

    @Override
    public List<Lunch> getPublicLunchList() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM dbo.lunch WHERE Lunchid IN(SELECT Lunch_id FROM dbo.Connector) AND isPublic = 1; ")) {

            ResultSet rs = ps.executeQuery();
            List<Lunch> lunchList = new ArrayList<Lunch>();
            while (rs.next()) {
                Lunch lunch = new Lunch();
                lunch.setTitle(rs.getString("title"));
                lunch.setDate(rs.getDate("date").toLocalDate());
                lunch.setTime(rs.getTime("time").toLocalTime());
                lunch.setPublic(rs.getBoolean("isPublic"));
                lunch.setPlace(rs.getString("place"));
                lunchList.add(lunch);



            }
            return lunchList;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in getUserID() in SQLServerProjectRepository. Could probably not execute query");
        }



    }

    @Override
    public long updateUser(long userID, User user) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE [dbo].[lunchusers] SET firstname=?, lastname=?, email=?, nickname = ?, state = ?, pass=?  WHERE ID=? ")) {

            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getNickname());
            ps.setInt(5, user.getState());
            ps.setString(6, user.getPassword());
            ps.setLong(7, userID);

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in depositToProject() in SQLServerProjectRepository. Could probably not execute query");
        }
    }

    @Override
    public long updateGroup(long groupID, UserGroup group) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE [dbo].[Usergroup] SET title=?, is_perm=? WHERE ID=? ")) {

            ps.setString(1, group.getTitle());
            ps.setBoolean(2, group.isPerm());
            ps.setLong(3, groupID);



            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in depositToProject() in SQLServerProjectRepository. Could probably not execute query");
        }


    }

    @Override
    public long addUserToGroup(long groupID, long userID) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[UserConnector] (usergroup_id, User_id)  VALUES (?,? )")) {

            ps.setLong(1, groupID);
            ps.setLong(2, userID);


            int rs = ps.executeUpdate();
            if (rs == 0) {

                System.out.println("error is 0");
            }

            return rs;


        } catch (SQLException e) {
            throw new LunchRepositoryException(e);
        }



    }

    @Override
    public long removeUserFromGroup(long groupID, long userID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM [dbo].[UserConnector] WHERE usergroup_id = ? AND User_id = ?")) {

            ps.setLong(1, groupID);
            ps.setLong(2, userID);


            int rs = ps.executeUpdate();
            if (rs == 0) {

                System.out.println("error is 0");
            }

            return rs;


        } catch (SQLException e) {
            throw new LunchRepositoryException(e);
        }






    }

    @Override
    public long updateLunch(long lunchID, Lunch lunch) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE [dbo].[lunchusers] SET title=?, date=?, time=?, isPublic = ?, place = ?  WHERE ID=? ")) {

            ps.setString(1, lunch.getTitle());

            ps.setDate(2, Date.valueOf(lunch.getDate()));
            ps.setTime(3, Time.valueOf(lunch.getTime()));
            ps.setBoolean(4, lunch.isPublic());
            ps.setString(5, lunch.getPlace());
            ps.setLong(6, lunchID);

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new LunchRepositoryException(e + "Trouble in depositToProject() in SQLServerProjectRepository. Could probably not execute query");
        }



    }

    @Override
    public long addUserToLunch(long lunchID, long userID) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[Connector] (Lunch_id, User_id, )  VALUES (?,? )")) {

            ps.setLong(1, lunchID);
            ps.setLong(2, userID);


            int rs = ps.executeUpdate();
            if (rs == 0) {

                System.out.println("error is 0");
            }

            return rs;


        } catch (SQLException e) {
            throw new LunchRepositoryException(e);
        }




    }

    @Override
    public long removeUserFromLunch(long lunchID, long userID) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM [dbo].[Connector] WHERE lunch_id = ? AND User_id = ?")) {

            ps.setLong(1, lunchID);
            ps.setLong(2, userID);


            int rs = ps.executeUpdate();
            if (rs == 0) {

                System.out.println("error is 0");
            }

            return rs;


        } catch (SQLException e) {
            throw new LunchRepositoryException(e);
        }




    }

    @Override
    public long addGroupToLunch(long lunchID, long groupID) {

        List<User> UserList =  getUsersInGroup(groupID);
        for(User user:UserList){
            addUserToLunch(lunchID, user.getUserid());


        }
        return 1;



    }
}
